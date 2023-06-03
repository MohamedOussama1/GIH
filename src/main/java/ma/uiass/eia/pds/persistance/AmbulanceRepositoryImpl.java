package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Ambulance.Ambulance;
import ma.uiass.eia.pds.model.Ambulance.AmbulanceType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class AmbulanceRepositoryImpl implements AmbulanceRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();

    public AmbulanceRepositoryImpl() {
    }

    @Override
    public void update_ambulance_etat(int id_ambulance, String etat) {

        SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ambulance ambu=session.find(Ambulance.class, id_ambulance);
        ambu.setEtatAmbulance(etat);
        session.update(ambu);
        session.getTransaction().commit();
        session.close();

    }

    public Ambulance getAmbulanceByImmatriculation(String immatriculation){
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Ambulance> criteriaQuery = criteriaBuilder.createQuery(Ambulance.class);
        Root<Ambulance> root = criteriaQuery.from(Ambulance.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("immatriculation"), immatriculation));
        Ambulance ambulance = session.createQuery(criteriaQuery).getSingleResult();
        session.close();
        return ambulance;
    }

    public Ambulance getAmbulanceById(int id) {
        Session session = sessionFactory.openSession();
        Ambulance ambulance = session.get(Ambulance.class, id);
        session.close();
        return ambulance;
    }

    public List<Ambulance> getAllAmbulances() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ambulance> criteria = builder.createQuery(Ambulance.class);
        Root<Ambulance> root = criteria.from(Ambulance.class);
        criteria.select(root);
        List<Ambulance> ambulances = session.createQuery(criteria).getResultList();
        session.close();
        return ambulances;
    }

    public void deleteAmbulance(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.find(Ambulance.class, id));
        session.getTransaction().commit();
        session.close();
    }

//    @Override
//    public EtatAmbulance createEtatAmbulance(String nom) {
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        EtatAmbulance etatAmbulance = new EtatAmbulance(StateName.valueOf(nom));
//        session.save(etatAmbulance);
//        session.getTransaction().commit();
//        session.close();
//        return etatAmbulance;
//    }

//    @Override
//    public String getEtatAmbulanceById(int id) {
//        Session session = sessionFactory.openSession();
//        String etatAmbulance = session.get(EtatAmbulance.class, id);
//        session.close();
//        return etatAmbulance;
//    }

//    @Override
//    public String getEtatAmbulanceByName(String nomEtat) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<EtatAmbulance> criteria = builder.createQuery(EtatAmbulance.class);
//        Root<EtatAmbulance> root = criteria.from(EtatAmbulance.class);
//        criteria.select(root);
//        criteria.where(builder.equal(root.get("nom"),nomEtat));
//        EtatAmbulance Etat = session.createQuery(criteria).getSingleResult();
//
//        return Etat;
//    }

//    @Override
//    public List<EtatAmbulance> getAllEtatAmbulance() {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<EtatAmbulance> criteria = builder.createQuery(EtatAmbulance.class);
//        Root<EtatAmbulance> root = criteria.from(EtatAmbulance.class);
//        criteria.select(root);
//        List<EtatAmbulance> etatAmbulances = session.createQuery(criteria).getResultList();
//        session.close();
//        return etatAmbulances;
//    }
    public Ambulance createAmbulance(String immatriculation, int km, LocalDate date_mise_en_service, AmbulanceType ambulanceType, String model) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ambulance ambulance = new Ambulance(immatriculation, date_mise_en_service, km, ambulanceType, model);
        session.save(ambulance);
        session.getTransaction().commit();
        session.close();
        return ambulance;
    }

    public void updateAmbulance(int id, String immatriculation, LocalDate date_mise_service, int km, AmbulanceType ambulanceType, String model) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ambulance ambulance = session.find(Ambulance.class, id);
        ambulance.setImmatriculation(immatriculation);
        ambulance.setKm(km);
        ambulance.setDate_mise_service(date_mise_service);
        ambulance.setAmbulanceType(ambulanceType);
        ambulance.setModel(model);
        session.update(ambulance);
        session.getTransaction().commit();
        session.close();
    }

}
