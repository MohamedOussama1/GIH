package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.demande.DemandeAffectation;
import ma.uiass.eia.pds.model.demande.DemandeDm;
import ma.uiass.eia.pds.model.demande.DetailDemandeDm;
import ma.uiass.eia.pds.model.demande.EtatDemande;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.DMItem;
import ma.uiass.eia.pds.model.dm.TypeDM;
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
//            configuration.configure("hibernate.cfg.xml");
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
            configuration.addAnnotatedClass(DM.class);
            configuration.addAnnotatedClass(DMItem.class);
            configuration.addAnnotatedClass(TypeDM.class);
            configuration.addAnnotatedClass(Reservation.class);
            configuration.addAnnotatedClass(FonctionLit.class);
            configuration.addAnnotatedClass(Patient.class);
            configuration.addAnnotatedClass(DemandeAffectation.class);
            configuration.addAnnotatedClass(DemandeDm.class);
            configuration.addAnnotatedClass(DetailDemandeDm.class);

            // Create Session Factory
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
