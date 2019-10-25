import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImageResizer {

    private List<File[]> splitFiles(File[] files) {
        int middle = files.length / 2;
        File[] firstArray = new File[middle];
        System.arraycopy(files, 0, firstArray, 0, firstArray.length);
        File[] secondArray = new File[files.length - middle];
        System.arraycopy(files, 0, secondArray, 0, secondArray.length);
        List<File[]> arrays = Arrays.asList(firstArray, secondArray);
        return arrays;
    }

    public void resizeWithExecutor(File[] files, String dstFolder) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        splitFiles(files).forEach(e -> {
            service.submit(() -> {
                try {
                    for (File file : files) {
                        BufferedImage image = ImageIO.read(file);
                        if (image == null) {
                            continue;
                        }

                        int newWidth = 300;
                        int newHeight = (int) Math.round(
                                image.getHeight() / (image.getWidth() / (double) newWidth)
                        );
                        BufferedImage newImage = new BufferedImage(
                                newWidth, newHeight, BufferedImage.TYPE_INT_RGB
                        );

                        int widthStep = image.getWidth() / newWidth;
                        int heightStep = image.getHeight() / newHeight;

                        for (int x = 0; x < newWidth; x++) {
                            for (int y = 0; y < newHeight; y++) {
                                int rgb = image.getRGB(x * widthStep, y * heightStep);
                                newImage.setRGB(x, y, rgb);
                            }
                        }
                        Path newFile = Paths.get(dstFolder, file.getName());
                        ImageIO.write(newImage, "jpg", newFile.toFile());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });
        service.shutdown();
        service.awaitTermination(40, TimeUnit.SECONDS);
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}

