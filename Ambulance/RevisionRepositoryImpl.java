package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Ambulance.Ambulance;
import ma.uiass.eia.pds.model.Ambulance.Revision;
import ma.uiass.eia.pds.model.Ambulance.TypeRevision;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.JSONObject;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.*;

public class RevisionRepositoryImpl implements RevisionRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    private AmbulanceRepository ambulanceRepository = new AmbulanceRepositoryImpl();
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
    public void updateRevision(int idRevision, LocalDate dateDevut, String description) {

    }

    @Override
    public void deleteRevision(int idRevision) {

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



//    @Override
//    public Map<String,Double> get_m01_m02_m12_revision() {
//
//        Map<String,Double> map_m01_m02=new HashMap<>();
//
//        /////////////////
//
//        Session session=sessionFactory.openSession();
//
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Revision> criteriaQuery = builder.createQuery(Revision.class);
//        Root<Revision> root = criteriaQuery.from(Revision.class);
//
//        Expression<Double> avgDaysDiff = builder.avg(
//                builder.diff(root.get("dateSortie"), root.get("dateRevision"))
//        );
//        criteriaQuery.multiselect(root.get("typeRevision"), avgDaysDiff);
//        criteriaQuery.groupBy(root.get("typeRevision"));
//
//        List<Double> results = session.createQuery(criteriaQuery).get;
//
//        ////////
//
//
//
//        List<Revision> lst_all_revision=this.getAll_revision();
//
//        List<Revision> lst_m01_F_NFC=new ArrayList<>();
//        List<Revision> lst_m02_F_NFL=new ArrayList<>();
//        //List<Revision> lst_m12_NFC_NFL=new ArrayList<>();
//        lst_all_revision.forEach(elt ->{
//            if(elt.getEtatAmbulance_0().equals("NFCD")){
//
//                lst_m01_F_NFC.add(elt);
//
//            }
//
//            if(elt.getEtatAmbulance_0().equals("NFLD")){
//
//                lst_m02_F_NFL.add(elt);
//
//            }
//
//        });
//
//
//
//
//    }


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






//    @Override
//    public Map<String, Double> get_m01_m02_m12_revision() {
//
//        Map<String, Double> map_m01_m02 = new HashMap<>();
//
//        Session session = sessionFactory.openSession();
////        CriteriaBuilder builder = session.getCriteriaBuilder();
////        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
////        Root<Revision> root = criteriaQuery.from(Revision.class);
////
////        Expression<Double> avgDaysDiff = builder.avg(
////                builder.diff(root.get("endDate"), root.get("startDate"))
////        );
//
//       // System.out.println(avgDaysDiff.toString());
//
//
//
////        criteriaQuery.multiselect(root.get("typeRevision"), avgDaysDiff);
////        criteriaQuery.groupBy(root.get("typeRevision"));
////        criteriaQuery.orderBy(builder.asc(root.get("typeRevision")));
////
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
//        Root<Revision> root = cq.from(Revision.class);
//        cq.multiselect(root.get("typeRevision"), cb.diff(root.get("endDate"), root.get("startDate")));
//        cq.groupBy(root.get("typeRevision"));
//       //cq.groupBy(root.get("id"));
//        cq.orderBy(cb.asc(root.get("typeRevision")));
//
//
//        List<Object[]> results = session.createQuery(cq).getResultList();
//
//        System.out.println(results.size());
//
//
//        System.out.println("======================================================");
//        System.out.println(results.getClass());
//        System.out.println(results.get(0).getClass());
//
//
//
//        for (Object[] result : results) {
//            System.out.println(result[0]+"   "+result[1]);
//
//            TypeRevision typerevision = (TypeRevision) result[0];
//            Double avgDays = (Double) result[1];
//            String test="";
//
//
//            if(typerevision.toString().contains("Courte durée")){
//
//                test="CD";
//
//            }
//
//            if(typerevision.toString().contains("Longue durée")){
//
//                test="LD";
//            }
//            map_m01_m02.put(test, avgDays);
//        }
//
//        session.close();
//
//        return map_m01_m02;
//    }







    //////




    public List<Object[]> getTypeRevisionWithDaysDiff() {


              Session session = sessionFactory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Revision> root = cq.from(Revision.class);
        cq.multiselect(root.get("typeRevision"), cb.toDouble(cb.diff(root.get("endDate"), root.get("startDate"))));
        cq.orderBy(cb.asc(root.get("typeRevision")));
        List<Object[]> results = session.createQuery(cq).getResultList();

        System.out.println(results.size());

        double test= 0;
        for (Object[] result : results) {

            System.out.println(result[0]+" "+result[1]);
//           // String typeRevision = (String) result[0];
//            double daysDiffValue = (double) result[1];
//            test+=daysDiffValue;
//
//            //System.out.println("Type Revision: " + typeRevision);
//            System.out.println("Days Difference: " + daysDiffValue);
//            System.out.println("--------------------");
        }



        return results;


//        Session session = sessionFactory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//
//
//        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
//        Root<Revision> root = criteriaQuery.from(Revision.class);
//
//        Expression<Double> daysDiff = builder.toDouble(builder.diff(root.get("endDate"), root.get("startDate")));
//        criteriaQuery.multiselect(root.get("typeRevision"), daysDiff);
//        criteriaQuery.orderBy(builder.asc(root.get("typeRevision")));
//
//        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
//
//        double test= 0;
//        for (Object[] result : results) {
//           // String typeRevision = (String) result[0];
//            double daysDiffValue = (double) result[1];
//            test+=daysDiffValue;
//
//            //System.out.println("Type Revision: " + typeRevision);
//            System.out.println("Days Difference: " + daysDiffValue);
//            System.out.println("--------------------");
//        }
//
//        System.out.println("gisibagisibagisibagisibagisibagisibagisibagisibagisibagisibagisibagisibagisibagisibagisibagisibagisiba");
//        System.out.println(test);
//        return results;
    }

//






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
}
