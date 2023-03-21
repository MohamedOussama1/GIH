package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitEquipe;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.dm.Dm;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;
import ma.uiass.eia.pds.model.etage.Etage;
import ma.uiass.eia.pds.model.patient.Patient;
import ma.uiass.eia.pds.model.reservation.Reservation;
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
            configuration.addAnnotatedClass(Batiment.class);
            configuration.addAnnotatedClass(Etage.class);
            configuration.addAnnotatedClass(Departement.class);
            configuration.addAnnotatedClass(Espace.class);
            configuration.addAnnotatedClass(Salle.class);
            configuration.addAnnotatedClass(Chambre.class);
            configuration.addAnnotatedClass(Lit.class);
            configuration.addAnnotatedClass(LitItem.class);
            configuration.addAnnotatedClass(LitEquipe.class);
            configuration.addAnnotatedClass(Dm.class);
            configuration.addAnnotatedClass(Reservation.class);
            configuration.addAnnotatedClass(FonctionLit.class);
            configuration.addAnnotatedClass(Patient.class);
            // Create Session Factory
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
