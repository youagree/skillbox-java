
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Loader {

    public static void main(String[] args) throws IOException, ParseException {

        String file = Paths.get("src", "main", "resources", "stations.html").toString();
        Document document = Jsoup.parse(Parser.parseFile(file));
        Element table = document.select("table").get(3);
        Elements rows = table.select("tr");
        rows.stream().skip(1).forEach((row) -> {
            Elements cols = row.select("td");
            String stationName = cols.get(1).text();
            String lineName = cols.get(0).child(1).attr("title");
            List<String> lineNumbers = cols.get(0).children().eachText();
            List<String> connectionsLineName = cols.get(0).children().eachAttr("title");
            List<String> connectionsNumber = cols.get(3).children().eachText();

            Parser.parseStation(stationName, lineNumbers, connectionsLineName);
            Parser.parseLines(lineName, lineNumbers);
            if (connectionsNumber.size() != 0) {
                Parser.parseConnections(cols, stationName);
            }
        });

        Parser.createJsonFile();
        Parser.jsonLinesParser();
    }
}
