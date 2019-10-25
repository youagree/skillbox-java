import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        String srcFolder = "Users/admin/Desktop/from";
        String dstFolder = "Users/admin/Desktop/to";

        Path pathFrom = Paths.get(srcFolder);
        Path pathTo = Paths.get(dstFolder);

        File srcDir = new File(pathFrom.toUri());
        try {
            File[] files = srcDir.listFiles();
            ImageResizer imageResizer = new ImageResizer();
            imageResizer.resizeWithExecutor(files, pathTo.toString());
        } catch (NullPointerException | InterruptedException e) {
            System.out.println("files not found");
        }
    }
}
