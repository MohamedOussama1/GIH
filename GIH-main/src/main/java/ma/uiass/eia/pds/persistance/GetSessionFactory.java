package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitEquipe;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.dm.Dm;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;
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
            configuration.addAnnotatedClass(Departement.class);
            configuration.addAnnotatedClass(Espace.class);
            configuration.addAnnotatedClass(Salle.class);
            configuration.addAnnotatedClass(Chambre.class);
            configuration.addAnnotatedClass(LitEquipe.class);
            configuration.addAnnotatedClass(Dm.class);

            // Create Session Factory
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
