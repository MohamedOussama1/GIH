package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Ambulance.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONObject;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RevisionRepositoryImpl implements RevisionRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    private AmbulanceRepository ambulanceRepository = new AmbulanceRepositoryImpl();

    @Override
    public void updateAmbulanceRevision(String immatriculation) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Revision.class);
//        Root<Revision> root = criteriaQuery.from(Revision.class);
//        Join<Revision, Ambulance> revisionAmbulanceJoin = root.join("ambulance");
//        criteriaQuery.where(criteriaBuilder.equal(revisionAmbulanceJoin.get("immatriculation"), immatriculation));
//        criteriaQuery.select(root);
//        List<Revision> revisions = session.createQuery(criteriaQuery).getResultList();
//        List<String> revisionsJson = new ArrayList<>();
//        revisions.forEach(revision -> {
//            JSONObject jsonRevision = new JSONObject(revision);
//            jsonRevision.put("nomEtat", revision.getEtatAmbulance_0());
//            revisionsJson.add(jsonRevision.toString());
//        });
//        System.out.println(immatriculation);
//        System.out.println(revisions);
//        System.out.println(revisionsJson);
//
//        return revisionsJson;
    }

    @Override
    public void createRevision(String immatriculation, LocalDate dateDebut, String typeRevision) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ambulance ambulance = ambulanceRepository.getAmbulanceByImmatriculation(immatriculation);
        Revision revision = new Revision(dateDebut, ambulance, TypeRevision.valueOf(typeRevision), ambulance.getEtatAmbulance());
        session.save(revision);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<String> getRevisionsByAmbulance(String immatriculation) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Revision.class);
        Root<Revision> root = criteriaQuery.from(Revision.class);
        Join<Revision, Ambulance> revisionAmbulanceJoin = root.join("ambulance");
        criteriaQuery.where(criteriaBuilder.equal(revisionAmbulanceJoin.get("immatriculation"), immatriculation));
        criteriaQuery.select(root);
        List<Revision> revisions = session.createQuery(criteriaQuery).getResultList();
        List<String> revisionsJson = new ArrayList<>();
        revisions.forEach(revision -> {
            JSONObject jsonRevision = new JSONObject(revision);
            jsonRevision.put("nomEtat", revision.getEtatAmbulance_0());
            revisionsJson.add(jsonRevision.toString());
        });
        System.out.println(immatriculation);
        System.out.println(revisions);
        System.out.println(revisionsJson);

        return revisionsJson;
    }

    @Override
    public void updateRevision(int idRevision, String typeRevision, LocalDate dateDebut, LocalDate dateSortie, int ancienKm, int nouvelKm, String description) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<Revision> update = cb.createCriteriaUpdate(Revision.class);
        Root<Revision> root = update.from(Revision.class);
        update.set(root.get("startDate"), dateDebut);
        update.set(root.get("endDate"), dateSortie);
        update.set(root.get("description"), description);
        update.set(root.get("typeRevision"), TypeRevision.valueOf(typeRevision));
        update.set(root.get("ancienKm"), ancienKm);
        update.set(root.get("nouvelKm"), nouvelKm);
        update.where(cb.equal(root.get("id"), idRevision));

        session.createQuery(update).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public int isInRevision(String immatriculation) {
        Ambulance ambulance = ambulanceRepository.getAmbulanceByImmatriculation(immatriculation);
        int isIn = ambulance.getRevision() == null ? 0 : 1;
        return isIn;
    }

    @Override
    public void deleteRevision(int idRevision) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.find(Revision.class, idRevision));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<String> getTypesRevision() {
        List<String> typesRevision;
        typesRevision = Arrays.stream(TypeRevision.values()).toList().stream().map(Enum::name).toList();
        return typesRevision;
    }




    // ============================   rachid          =========================


    @Override
    public List<Revision> getAll_revision() {
        Session session=sessionFactory.openSession();

        CriteriaBuilder builder=session.getCriteriaBuilder();

        CriteriaQuery<Revision> criteriaq=builder.createQuery(Revision.class);

        Root<Revision> root=criteriaq.from(Revision.class);


        criteriaq.select(root);

        List<Revision> lst=session.createQuery(criteriaq).getResultList();

        return lst;
    }



    @Override
    public List<List<Revision>> get_m01_m02_m12_revision() {
        List<Revision> lst_all_revision=this.getAll_revision();

        List<Revision> lst_m01_F_NFC=new ArrayList<>();
        List<Revision> lst_m02_F_NFL=new ArrayList<>();
        //List<Revision> lst_m12_NFC_NFL=new ArrayList<>();
        lst_all_revision.forEach(elt ->{
            if(elt.getEtatAmbulance_0().equals("NFCD")){

                lst_m01_F_NFC.add(elt);

            }

            if(elt.getEtatAmbulance_0().equals("NFLD")){

                lst_m02_F_NFL.add(elt);

            }

        });

        List<List<Revision>> lst_divise=new ArrayList<>();
        lst_divise.add(lst_m01_F_NFC);
        lst_divise.add(lst_m02_F_NFL);

        return lst_divise;
    }




    @Override
    public Revision get_last_revision_by_ambulance(Ambulance ambulance) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Revision> criteriaQuery = criteriaBuilder.createQuery(Revision.class);
        Root<Revision> root = criteriaQuery.from(Revision.class);

        // Add criteria for ambulance and order by startDate in descending order
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("ambulance"), ambulance))
                .orderBy(criteriaBuilder.desc(root.get("startDate")));

        Query<Revision> query = session.createQuery(criteriaQuery);
        query.setMaxResults(1); // Fetch only the first result

        Revision revision = query.uniqueResult();

        session.close(); // Close the session

        return revision;
    }

    @Override
    public void endCurrentRevision(String immatriculation, LocalDate endDate) {
        Ambulance ambulance = ambulanceRepository.getAmbulanceByImmatriculation(immatriculation);
        Session session = sessionFactory.openSession();
        Revision revision = ambulance.getRevision();
        revision.setEndDate(endDate);
        session.save(revision);
        session.getTransaction().commit();
        session.close();
    }
}
