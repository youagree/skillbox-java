import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Loader {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://lenta.ru/").get();
        Elements elements = doc.select("img");

        elements.forEach(element -> {
            if (element.attr("src").contains("http")
                    && (element.attr("src").contains("jpg")
                    || element.attr("src").contains("png"))) {
                String[] name = element.attr("src").split("/");
                try {
                    URL url = new URL(element.attr("src"));
                    InputStream inputStream = url.openStream();
                    Files.copy(inputStream, Paths.get("img_copy/" + name[name.length - 1]),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
