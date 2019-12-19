import static java.lang.System.out;
import static java.lang.System.setProperty;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {

    private static FileSystem hdfs;
    private Path path;

    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param containerName - the path to the root of HDFS,
     *                      for example, hdfs://localhost:32771
     */
    public FileAccess(String containerName, String port) throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        setProperty("HADOOP_USER_NAME", "root");
        hdfs = FileSystem.get(new URI("hdfs://localhost:8020"), configuration);
        path = new Path("hdfs://" + containerName + ":" + port + "/");
    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public String create(String path) throws IOException {
        try (FSDataOutputStream dataOutputStream = hdfs.create(new Path(path))) {
            return "success load, size: " + dataOutputStream.size();
        }
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws IOException {
        if (hdfs.exists(new Path(path))) {
            out.println("write process start into " + path);
            try (FSDataOutputStream fileOutputStream = hdfs.append(new Path(path))) {
                fileOutputStream.writeBytes(content);
            }
            out.println("write process finish");
        } else {
            out.println("create new file because does not exist yet " + path);
            try (FSDataOutputStream fileOutputStream = hdfs.create(new Path(path))) {
                fileOutputStream.writeBytes(content);
            }
            out.println("write process finish");
        }
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws IOException {
        try (FSDataInputStream inputStream = hdfs.open(new Path(path))){
            String out = IOUtils.toString(inputStream, "UTF-8");
            return out;
        }
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException {
        try{
            hdfs.delete(new Path(path), true);
        } finally {
            hdfs.close();
        }
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws IOException {
        try {
            FileStatus fileStatus = hdfs.getFileStatus(new Path(path));
            if (fileStatus.isDirectory()) {
                out.println("is directory " + path);
                return true;
            } else {
                out.println("not is directory " + path);
                return false;
            }
        } finally {
            hdfs.close();
        }
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> getFilesList(String path) throws IOException {
        List<String> fileList = new ArrayList<String>();
        try {
            FileStatus[] fileStatus = hdfs.listStatus(new Path(path));
            for (FileStatus fileStat : fileStatus) {
                if (fileStat.isDirectory()) {
                    String filePath = fileStat.getPath().toString();
                    fileList.addAll(getFilesList(filePath));
                } else {
                    fileList.add(fileStat.getPath().toString());
                }
            }
            return fileList;
        } finally {
            hdfs.close();
        }
    }
}
