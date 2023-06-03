package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.demande.DemandeDm;
import ma.uiass.eia.pds.model.demande.DetailDemandeDm;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.fournisseur.Fournisseur;
import ma.uiass.eia.pds.model.livraison.DetailLivraison;
import ma.uiass.eia.pds.model.livraison.LivraisonDM;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LivraisonRepositoryImpl implements LivraisonRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    private DMRepository DMR = new DMRepositoryImpl();
    private FournisseurRepository FR = new FournisseurRepositoryImpl();

    public LivraisonRepositoryImpl(){}



    @Override
    public void saveLivraison(JSONArray detailLivraisonList, String fournisseur) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        LivraisonDM livraisonDM = new LivraisonDM();
        Fournisseur fournisseur1 = FR.getFournisseurByName(fournisseur);
        livraisonDM.setFournisseur(fournisseur1);
        livraisonDM.setDateLivraison(LocalDateTime.now());
        session.save(livraisonDM);
        session.getTransaction().commit();

        session.beginTransaction();


        // Convert the JSONArray to a list of DetailLivraison objects
        List<DetailLivraison> detailLivraisons = new ArrayList<>();
        for (int i = 0; i < detailLivraisonList.length(); i++) {
            JSONObject jsonObject = new JSONObject(detailLivraisonList.get(i).toString());
            System.out.println(livraisonDM.getId());
            DetailLivraison detailLivraison = saveDetailLivraison(jsonObject.getInt("qte"), jsonObject.getString("dm"), livraisonDM.getId());
            detailLivraisons.add(detailLivraison);
        }


        session.save(livraisonDM);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public DetailLivraison saveDetailLivraison(int qte, String dm, int idLivraison) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DM dm1 = DMR.getDMByName(dm);
        LivraisonDM livraisonDm = session.find(LivraisonDM.class, idLivraison);
        DetailLivraison detailLivraison = new DetailLivraison(qte, dm1, livraisonDm);
        livraisonDm.getDetailLivraisons().add(detailLivraison);
        session.save(detailLivraison);
        session.save(livraisonDm);
        session.getTransaction().commit();
        session.close();
        return detailLivraison;
    }

    @Override
    public List<LivraisonDM> getAllLivraison() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<LivraisonDM> criteria = builder.createQuery(LivraisonDM.class);
        Root<LivraisonDM> root = criteria.from(LivraisonDM.class);
        criteria.select(root);
        List<LivraisonDM> livraisons = session.createQuery(criteria).getResultList();
//        List<String> demandesDm = new ArrayList<>();
//        demandes.forEach(demande -> {
//            JSONObject jsonDemande = new JSONObject();
//            jsonDemande.put("demande", new JSONObject(demande));
//            CriteriaQuery<DetailDemandeDm> criteriaQuery = builder.createQuery(DetailDemandeDm.class);
//            Root<DetailDemandeDm> detailDemandeDmRoot = criteriaQuery.from(DetailDemandeDm.class);
//            criteriaQuery.where(builder.equal(detailDemandeDmRoot.get("demande"), demande.getId()));
//            List<DetailDemandeDm> detailsDemandeDm = session.createQuery(criteriaQuery).getResultList();
//            jsonDemande.put("detailsDemande", detailsDemandeDm);
//            demandesDm.add(jsonDemande.toString());
//        });
        session.close();
        return livraisons;


//        Session session = sessionFactory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<DemandeDm> criteria = builder.createQuery(DemandeDm.class);
//        Root<DemandeDm> root = criteria.from(DemandeDm.class);
//        Join<DemandeDm, Departement> joinDepartment = root.join("departement");
//        criteria.select(root);
//        criteria.where(builder.like(joinDepartment.get("nomDepartement"), nomDepartement));
//        List<DemandeDm> demandes = session.createQuery(criteria).getResultList();
//        List<String> demandesDm = new ArrayList<>();
//        demandes.forEach(demande -> {
//            JSONObject jsonDemande = new JSONObject();
//            jsonDemande.put("demande", new JSONObject(demande));
//            CriteriaQuery<DetailDemandeDm> criteriaQuery = builder.createQuery(DetailDemandeDm.class);
//            Root<DetailDemandeDm> detailDemandeDmRoot = criteriaQuery.from(DetailDemandeDm.class);
//            criteriaQuery.where(builder.equal(detailDemandeDmRoot.get("demande"), demande.getId()));
//            List<DetailDemandeDm> detailsDemandeDm = session.createQuery(criteriaQuery).getResultList();
//            jsonDemande.put("detailsDemande", detailsDemandeDm);
//            demandesDm.add(jsonDemande.toString());
//        });
//        session.close();
//        return demandesDm;


    }

}
