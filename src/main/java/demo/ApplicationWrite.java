package demo;

import entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationWrite {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();

            try {
                Student student = new Student("FFF", "LLL", "test@mail.com0");

                session.beginTransaction();
                session.save(student);
                session.getTransaction().commit();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } finally {
            factory.close();
        }

    }
}
