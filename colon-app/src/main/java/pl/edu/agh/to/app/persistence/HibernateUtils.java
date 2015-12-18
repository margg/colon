package pl.edu.agh.to.app.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by sjchmiela on 17.12.2015.
 */
public class HibernateUtils {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            String host = (System.getenv("DB_HOST") == null) ? "localhost" : System.getenv("DB_HOST");
            String database = (System.getenv("DB_DATABASE") == null) ? "colon_dev" : System.getenv("DB_DATABASE");
            String port = (System.getenv("DB_PORT") == null) ? "3306" : System.getenv("DB_PORT");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false");

            String username = (System.getenv("DB_USERNAME") == null) ? "root" : System.getenv("DB_USERNAME");
            configuration.setProperty("hibernate.connection.username", username);

            String password = (System.getenv("DB_PASSWORD") == null) ? "" : System.getenv("DB_PASSWORD");
            configuration.setProperty("hibernate.connection.password", password);

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);

            getSession().close();
        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return ourSessionFactory;
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
}