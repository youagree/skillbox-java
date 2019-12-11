import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Loader {
    public static Queue<String> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        int coreCount = Runtime.getRuntime().availableProcessors() * 2;
        long startTime = System.currentTimeMillis();
        Path targetPath = Paths.get("res\\num1_multi.txt");
        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);
        try {
            executorService.submit(() -> {
                generateNumbers();
                String str;
                while (queue.size() > 0) {
                    try {
                        if ((str = queue.poll()) != null) {
                            Files.write(targetPath, str.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.shutdown();
            executorService.awaitTermination(30, TimeUnit.SECONDS);
            System.out.println((System.currentTimeMillis() - startTime) + " ms\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void generateNumbers() {
        for (int i = 0; i < 100; i++) {
            char[] letters = new char[]{'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            StringBuilder carNumber = new StringBuilder();
            char[] var3 = letters;
            int var4 = letters.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                char firstLetter = var3[var5];
                char[] var7 = letters;
                int var8 = letters.length;

                for (int var9 = 0; var9 < var8; ++var9) {
                    char secondLetter = var7[var9];
                    char[] var11 = letters;
                    int var12 = letters.length;

                    for (int var13 = 0; var13 < var12; ++var13) {
                        char thirdLetter = var11[var13];

                        for (int number = 1; number < 1000; ++number) {
                            carNumber.append(firstLetter)
                                    .append(padNumber(number, 3))
                                    .append(secondLetter).append(thirdLetter)
                                    .append(padNumber(i, 2))
                                    .append("\n");
                        }
                    }
                }
            }
            queue.add(carNumber.toString());
        }
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; ++i) {
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
    }
}
