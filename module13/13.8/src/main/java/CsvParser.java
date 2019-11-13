import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CsvParser {


    public Set<Student> parseFile() throws IOException {
        Set<Student> students = new HashSet<>();
        String path = "src/main/resources/mongo.csv";
        List<String> strings = Files.readAllLines(Paths.get(path));

        for (String s : strings) {
            String[] line = s.split(",", 3);
            String name = line[0];
            int age = Integer.parseInt(line[1]);
            List<String> courses = getCourses(line[2]);
            students.add(new Student(name, age, courses));
        }
        return students;
    }

    private List<String> getCourses(String string) {
        String[] split = string.replaceAll("\"", "").split(",");
        List<String> courses = new ArrayList<>(Arrays.asList(split));
        return courses;
    }
}