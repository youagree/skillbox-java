import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Loader {
    public static void main(String[] args) throws IOException{
        File source = new File("C:\\Users\\admin\\Desktop\\work\\SkillboxJava");
        File dest = new File("C:\\Users\\admin\\Desktop\\test");
        copyFile(source, dest);
    }

    public static void copyFile(File source, File dest) throws IOException {
        FileUtils.copyDirectoryToDirectory(source, dest);
    }
}
