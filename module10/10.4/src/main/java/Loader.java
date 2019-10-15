import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pojo.Course;
import pojo.PurchaseList;
import pojo.Student;
import pojo.Subscription;
import pojo.Teacher;

public class Loader {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(PurchaseList.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Subscription.class)
                .addAnnotatedClass(Teacher.class)
                .buildSessionFactory();

        try {

        } finally {
            factory.close();
        }
    }
}
