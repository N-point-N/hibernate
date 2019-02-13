package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ApplicationQuery {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();

            try {
                session.beginTransaction();

                List<Student> theStudents = session.createQuery("from  Student s where s.lastName='Wall'").list();

                for(Student tempStudent: theStudents){
                    System.out.println(tempStudent);
                }
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
