package pl.edu.agh.to.app.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.to.app.model.Student;

/**
 * Created by aalices on 2016-01-11.
 */
public class StudentDao {

    public Student getStudent(long id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Student student = (Student) session.get(Student.class, id);
        transaction.commit();
        session.close();
        return student;

    }
}
