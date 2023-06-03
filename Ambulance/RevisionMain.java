package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Ambulance.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class RevisionMain {
    public static void main(String[] args) {

        RevisionRepositoryImpl revisionRepository = new RevisionRepositoryImpl();






        AmbulanceRepositoryImpl dd=new AmbulanceRepositoryImpl();

        Ambulance ambu=dd.getAmbulanceById(1);


        EtatAmbulance etat=ambu.getEtat_object();

        etat.setAmbulance(ambu);


        double y=etat.predict_Y();
//
         System.out.println(y);

       // System.out.println(revisionRepository.get_m01_m02_m12_revision());




//        SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
//
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        ambu.setEtatAmbulance("NFLD");
//        session.update(ambu);
//        session.getTransaction().commit();
//        session.close();



}
}
