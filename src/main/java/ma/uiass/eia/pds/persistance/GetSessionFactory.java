package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Singleton pattern for SessionFactory

public class GetSessionFactory {

    private static SessionFactory sessionFactory;

    private GetSessionFactory(){}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            // Create Configuration
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.connection.autocommit", "true");
            configuration.addAnnotatedClass(Lit.class);

            // Create Session Factory
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
