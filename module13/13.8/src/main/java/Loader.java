import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.io.IOException;
import java.util.Set;

public class Loader {

    public static void main(String[] args) throws IOException {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        DB local = mongoClient.getDB("test");
        DBCollection students = local.getCollection("students");
        fillMongoDB(students);
        printInfo(students);
        mongoClient.close();
    }

    private static void fillMongoDB(DBCollection collection)
            throws IOException {
        CsvParser csvParser = new CsvParser();
        Set<Student> students = csvParser.parseFile();
        for (Student student : students) {
            collection.insert(toDBObject(student));
        }
    }

    private static void printInfo(DBCollection students) {
        System.out.println("Количество студентов: " + students.count() + " человек.");

        int age = students.find(new BasicDBObject("age", new BasicDBObject("$gt", 40))).count();
        System.out.println("Количество студентов старше 40 лет : " + age + " человек.");

        DBCursor youngStudent = students.find().sort(new BasicDBObject("age", 1)).limit(1);
        System.out.println("Имя самого молодого студента: " + youngStudent.one().get("name"));

        DBCursor oldStudent = students.find().sort(new BasicDBObject("age", -1)).limit(1);
        System.out.println("Список куров самого старого студента " + oldStudent.one().get("courses"));
    }

    private static DBObject toDBObject(Student student) {
        return new BasicDBObject("name", student.getName())
                .append("age", student.getAge())
                .append("courses", student.getCourses());
    }
}