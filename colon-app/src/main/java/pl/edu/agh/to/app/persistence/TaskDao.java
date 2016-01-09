package pl.edu.agh.to.app.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.to.app.model.Task;

import java.util.List;

public class TaskDao {

    public Task getTask(long id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Task task = (Task) session.createQuery("select t from Task t where t.id=:id").setParameter("id", id).uniqueResult();
        transaction.commit();
        session.close();
        return task;

    }

    public List<Task> getTasks(long id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        List<Task> tasks =  session.createQuery("select t from Task t").list();
        transaction.commit();
        session.close();
        return tasks;

    }
}