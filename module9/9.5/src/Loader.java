import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Loader {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("C:\\Users\\admin\\Desktop\\work\\SkillboxJava");
        long size = Files.walk(folder)
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
        System.out.println(size + " байт");
    }
}
