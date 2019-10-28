import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveTask;

@Data
@AllArgsConstructor
public class LinkCollector extends RecursiveTask<String> {

    private String url;
    private static String startUrl;
    private static CopyOnWriteArraySet<String> allLinks = new CopyOnWriteArraySet<>();

    public LinkCollector(String url, String startUrl) {
        this.url = url.trim();
        LinkCollector.startUrl = startUrl.trim();
    }

    @Override
    protected String compute() {
        StringBuffer sb = new StringBuffer(url + "\n");
        Set<LinkCollector> subTask = new HashSet<>();
        getChildren(subTask);
        subTask.forEach(e -> sb.append(e.join()));
        return sb.toString();
    }

    private void getChildren(Set<LinkCollector> subTask) {
        Document doc;
        Elements elements;
        try {
            Thread.sleep(200);
            doc = Jsoup.connect(url).get();
            elements = doc.select("a");
            elements.forEach(el -> {
                String attr = el.attr("abs:href");
                if (!attr.isEmpty() && attr.startsWith(startUrl) &&
                        !allLinks.contains(attr) && !attr.contains("#")) {
                    LinkCollector linkCollector = new LinkCollector(attr);
                    linkCollector.fork();
                    subTask.add(linkCollector);
                    allLinks.add(attr);
                }
            });
        } catch (InterruptedException | IOException ignored) {
        }
    }
}
