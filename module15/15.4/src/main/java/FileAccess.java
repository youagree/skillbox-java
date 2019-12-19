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

import static java.lang.System.out;
import static java.lang.System.setProperty;

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
        hdfs = FileSystem.get(new URI("hdfs://48c4cfa049db:8020"), configuration);
        path = new Path("hdfs://" + containerName + ":" + port + "/");
    }

    /**
     * Creates empty file or directory
     *
     * @param extraPath
     */
    public String create(String extraPath) throws IOException {
        try (FSDataOutputStream dataOutputStream = hdfs.create(new Path(path + extraPath))) {
            return "success load, size: " + dataOutputStream.size();
        }
    }

    /**
     * Appends content to the file
     *
     * @param extraPath
     * @param content
     */
    public void append(String extraPath, String content) throws IOException {
        if (hdfs.exists(new Path(path + extraPath))) {
            out.println("write process start into " + extraPath);
            try (FSDataOutputStream fileOutputStream = hdfs.append(new Path(path + extraPath))) {
                fileOutputStream.writeBytes(content);
            }
            out.println("write process finish");
        } else {
            out.println("create new file because does not exist yet " + extraPath);
            try (FSDataOutputStream fileOutputStream = hdfs.create(new Path(path + extraPath))) {
                fileOutputStream.writeBytes(content);
            }
            out.println("write process finish");
        }
    }

    /**
     * Returns content of the file
     *
     * @param extraPath
     * @return
     */
    public String read(String extraPath) throws IOException {
        try (FSDataInputStream inputStream = hdfs.open(new Path(path + extraPath))) {
            String out = IOUtils.toString(inputStream, "UTF-8");
            return out;
        }
    }

    /**
     * Deletes file or directory
     *
     * @param extraPath
     */
    public void delete(String extraPath) throws IOException {
        try {
            hdfs.delete(new Path(path + extraPath), true);
        } finally {
            hdfs.close();
        }
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param extraPath
     * @return
     */
    public boolean isDirectory(String extraPath) throws IOException {
        try {
            FileStatus fileStatus = hdfs.getFileStatus(new Path(path + extraPath));
            if (fileStatus.isDirectory()) {
                out.println("is directory " + extraPath);
                return true;
            } else {
                out.println("not is directory " + extraPath);
                return false;
            }
        } finally {
            hdfs.close();
        }
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param extraPath
     * @return
     */
    public List<String> getFilesList(String extraPath) throws IOException {
        List<String> fileList = new ArrayList<String>();
        try {
            FileStatus[] fileStatus = hdfs.listStatus(new Path(path + extraPath));
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
