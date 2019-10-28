import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();
        int numThreads = sc.nextInt();
        LinkCollector linkExecutor = new LinkCollector(url, url);
        String siteMap = numThreads == 0 ? new ForkJoinPool().invoke(linkExecutor)
                : new ForkJoinPool(numThreads).invoke(linkExecutor);
        writeFiles(siteMap);
    }

    private static void writeFiles(String map) {
        String filePath = "siteMap.txt";
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
