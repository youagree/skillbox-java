import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pojo.Course;
import pojo.PurchaseList;
import pojo.Student;
import pojo.StudentsCourses;
import pojo.Subscription;
import pojo.Teacher;

import java.util.Date;
import java.util.List;

public class Loader {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(PurchaseList.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Subscription.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(StudentsCourses.class)
                .buildSessionFactory();

        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Object[]> purchaseLists = session.createQuery("SELECT s.id, c.id, p.price,  p.subscriptionDate " +
                    "FROM PurchaseList p " +
                    "join Student s on (s.name = p.studentName) " +
                    "join Course c on (c.name = p.courseName)", Object[].class).getResultList();
              purchaseLists.stream().forEach(row -> {
                  Student student = session.get(Student.class, (int) row[0]);
                  Course course = session.get(Course.class, (int) row[1]);
                  StudentsCourses studentsCourses = StudentsCourses.builder()
                          .id(new StudentsCourses.Id((int) row[0], (int) row [1]))
                          .student(student)
                          .course(course)
                          .price((int) row [2])
                          .subscriptionDate((Date) row[3])
                          .build();
                  session.save(studentsCourses);
                  System.out.println(studentsCourses);
              });
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
