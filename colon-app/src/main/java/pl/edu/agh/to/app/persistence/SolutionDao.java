package pl.edu.agh.to.app.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.to.app.model.Solution;

public class SolutionDao {
    public Solution saveSolution(Solution solution) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(solution);
        transaction.commit();
        session.close();
        return solution;
    }
}
