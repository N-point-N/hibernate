package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationRead {

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
                if (student == null) {
                    System.out.println("Student is not exist");
                }
                System.out.println(student.toString());
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
