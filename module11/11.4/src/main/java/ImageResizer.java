import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class ImageResizer {

    public void resize(File[] files, String dstFolder) {
        long start = System.currentTimeMillis();
        splitFiles(files).stream().forEach(e -> {
            new Thread(() -> {
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

                        File newFile = new File(dstFolder + "/" + file.getName());
                        ImageIO.write(newImage, "jpg", newFile);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).run();
        });
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

    private ArrayList<File[]> splitFiles(File[] files) {
        int middle = files.length / 2;

        File[] firstArray = new File[middle];
        System.arraycopy(files, 0, firstArray, 0, firstArray.length);

        File[] secondArray = new File[files.length - middle];
        System.arraycopy(files, 0, secondArray, 0, secondArray.length);

        ArrayList<File[]> arrays = new ArrayList<>();
        arrays.add(firstArray);
        arrays.add(secondArray);
        return arrays;
    }
}
