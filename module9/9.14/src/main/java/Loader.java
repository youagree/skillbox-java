import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Line;
import dto.Metro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Loader {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static String fileName = Paths.get("src", "main", "resources", "underground.json").toString();
    private static JSONParser jsonParser = new JSONParser();
    private static Metro metro;
    private static Parser parser = new Parser();

    public static void main(String[] args) throws IOException, ParseException {
        String file = Paths.get("src", "main", "resources", "stations.html").toString();
        parser.parseInputDataHtmlFile(file);
        createJsonFile();
        readJsonAndPrintStations();
    }

    static void createJsonFile() throws IOException {
        metro = new Metro(parser.getLines(), parser.getStations(), parser.getConnection());
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(GSON.toJson(metro));
        }
    }

    static void readJsonAndPrintStations() throws ParseException {
        JSONObject jsonObject = (JSONObject) jsonParser.parse(parser.parseFile(fileName));
        Map<String, List<String>> stations = (Map<String, List<String>>) jsonObject.get("stations");
        checkStations(stations);
    }

    private static void checkStations(Map<String, List<String>> stations) {
        for (String lineId : stations.keySet()) {
            JSONArray stationsArray = (JSONArray) stations.get(lineId);
            for (Line line : metro.getLines()) {
                printLineDetails(lineId, stationsArray, line);
            }
        }
    }

    private static void printLineDetails(String lineId, JSONArray stationsArray, Line line) {
        if (line.getId().equals(lineId)) {
            System.out.println("Линия " + lineId + " " + line.getName()
                    + " -> количество станций: " + stationsArray.size());
        }
    }
}
