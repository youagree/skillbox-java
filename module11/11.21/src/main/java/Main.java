import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();
        int numThreads = sc.nextInt();
        LinkCollector linkExecutor = new LinkCollector(url, url);
        String siteMap = numThreads == 0 ? new ForkJoinPool().invoke(linkExecutor)
                : new ForkJoinPool(numThreads).invoke(linkExecutor);
        writeFiles(siteMap);
    }

    private static void writeFiles(String map) throws IOException {
        Path path = Paths.get("C:/", "temp/", "siteMap.txt");
        Files.writeString(path, map, StandardOpenOption.APPEND);
    }
}
