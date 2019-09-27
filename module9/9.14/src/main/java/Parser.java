import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Line;
import dto.Metro;
import dto.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static List<Line> lines = new LinkedList<>();
    private static Map<String, List<String>> stations = new TreeMap<>();
    private static List<List<Station>> connection = new ArrayList<>();
    private static Metro metro;

    static void createJsonFile() throws IOException {

        metro = new Metro(Parser.lines, Parser.stations, Parser.connection);
        try (FileWriter file = new FileWriter("src\\main\\resources\\map.json")) {
            file.write(GSON.toJson(metro));
        }
    }

    static void parseConnections(Elements cols, String stationName) {
        List<String> connectionsLine = cols.get(3).children().eachAttr("title");
        List<String> connectionsNumber = cols.get(3).children().eachText();
        List<String> lineNumbers = cols.get(0).children().eachText();
        String lineId = lineNumbers.get(1);
        if (connectionsNumber.size() != 0) {
            List<Station> temp = new ArrayList<>();
            temp.add(new Station(lineId, stationName));
            for (int i = 0; i < connectionsNumber.size(); i++) {
                temp.add(new Station(connectionsNumber.get(i), connectionsLine.get(i)));
            }
            connection.add(temp);
        }
    }

    static void parseLines(String lineName, List<String> lineNumbers) {
        Line line = new Line(lineNumbers.get(0), lineName);
        if (!lines.contains(line)) lines.add(line);
    }

    static void parseStation(String stationName, List<String> lineNumbers, List<String> connectionsLineName) {
        String lineId = lineNumbers.get(0);
        if (!stations.containsKey(lineId)) {
            stations.put(lineId, new ArrayList<>());
            stations.get(lineId).add(stationName);
        } else stations.get(lineId).add(stationName);

        if (connectionsLineName.size() == 2) {
            if (!stations.containsKey(lineNumbers.get(1)))
                stations.put(lineNumbers.get(1), new ArrayList<>());
            else stations.get(lineNumbers.get(1)).add(stationName);
        }
    }

    static String parseFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> sb.append(line).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    static void JsonParser() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(parseFile("src\\main\\resources\\map.json"));

        Map<String, List<String>> stations = (Map<String, List<String>>) jsonObject.get("stations");
        for (String lineId : stations.keySet()) {
            JSONArray stationsArray = (JSONArray) stations.get(lineId);
            for (Line line : metro.getLines()) {
                if (line.getId().equals(lineId)) {
                    System.out.println("Линия " + lineId + " " + line.getName()
                            + " -> количество станций: " + stationsArray.size());
                }
            }
        }
    }
}

