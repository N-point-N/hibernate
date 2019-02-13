package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationDelete {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();

            try {
                session.beginTransaction();

                System.out.println((session.createQuery("delete from Student where first_name='FFF'").executeUpdate()));
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
