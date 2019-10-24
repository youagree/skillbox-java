import java.io.File;

public class Main {
    public static void main(String[] args) {
        String srcFolder = "/users/admin/Desktop/src";
        String dstFolder = "/users/admin/Desktop/dst";

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        ImageResizer imageResizer = new ImageResizer();
        imageResizer.resize(files, dstFolder);
        imageResizer.resizeWithParallelStream(files, dstFolder);
    }
}
