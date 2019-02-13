package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationUpdate {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();

            try {
                session.beginTransaction();
                Student student = (Student) session.get(Student.class, 2);
                if (student != null) {
                    student.setFirstName("Scooby");
                }
                System.out.println(student.toString());

                session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();
            } finally {
                if (session.isOpen()) {
                    session.getTransaction().commit();
                }
            }
        } finally {
            factory.close();
        }

    }
}
