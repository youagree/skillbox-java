import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pojo.Course;

public class Loader {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // CRUD
        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Course course = session.get(Course.class, 1);
            session.getTransaction().commit();
            System.out.println(course);
        } finally {
            factory.close();
            session.close();
        }
    }
}
