import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import pojo.Course;
import pojo.PurchaseList;
import pojo.Student;
import pojo.StudentsCourses;
import pojo.Subscription;
import pojo.Teacher;

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
            List<PurchaseList> purchaseLists = session.createQuery("from PurchaseList").getResultList();
            purchaseLists.stream().forEach(e ->{
                DetachedCriteria studentsCriteria = DetachedCriteria.forClass(Student.class)
                        .add(Restrictions.eq("name", e.getStudentName()));
                Student student = (Student) studentsCriteria.getExecutableCriteria(session).list().stream()
                        .findFirst().get();

                DetachedCriteria coursesCriteria = DetachedCriteria.forClass(Course.class)
                        .add(Restrictions.eq("name", e.getCourseName()));
                Course course = (Course) coursesCriteria.getExecutableCriteria(session).list().stream()
                        .findFirst().get();
                System.out.println(course.getName());

                StudentsCourses sc = new StudentsCourses(
                        new StudentsCourses.Id(student.getId(), course.getId()), student, course,
                        course.getPrice(), e.getSubscriptionDate());
                session.save(sc);
            });
        } finally {
            factory.close();
        }
    }
}
