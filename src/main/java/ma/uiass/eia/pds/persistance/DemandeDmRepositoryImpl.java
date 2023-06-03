package ma.uiass.eia.pds.persistance;

import jakarta.json.JsonObject;
import ma.uiass.eia.pds.model.demande.DemandeDm;
import ma.uiass.eia.pds.model.demande.DetailDemandeDm;
import ma.uiass.eia.pds.model.demande.EtatDemande;
import ma.uiass.eia.pds.model.demande.EtatDetail;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.TypeDM;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DemandeDmRepositoryImpl implements DemandeDmRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    DMRepository dmRepository = new DMRepositoryImpl();
    DepartementRepository departementRepository = new DepartementRepositoryImpl();
    @Override
    public List<String> getAllDemandes() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DemandeDm> criteria = builder.createQuery(DemandeDm.class);
        Root<DemandeDm> root = criteria.from(DemandeDm.class);
        criteria.select(root);
        List<DemandeDm> demandes = session.createQuery(criteria).getResultList();
        List<String> demandesDm = new ArrayList<>();
        demandes.forEach(demande -> {
            JSONObject jsonDemande = new JSONObject();
            jsonDemande.put("demande", demande);
            CriteriaQuery<DetailDemandeDm> criteriaQuery = builder.createQuery(DetailDemandeDm.class);
            Root<DetailDemandeDm> detailDemandeDmRoot = criteriaQuery.from(DetailDemandeDm.class);
            criteriaQuery.where(builder.equal(detailDemandeDmRoot.get("demande"), demande.getId()));
            List<DetailDemandeDm> detailsDemandeDm = session.createQuery(criteriaQuery).getResultList();
            jsonDemande.put("detailsDemande", detailsDemandeDm);
            demandesDm.add(jsonDemande.toString());
        });
        session.close();
        return demandesDm;
    }

    @Override
    public List<String> getDemandesByDepartement(String nomDepartement) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DemandeDm> criteria = builder.createQuery(DemandeDm.class);
        Root<DemandeDm> root = criteria.from(DemandeDm.class);
        Join<DemandeDm, Departement> joinDepartment = root.join("departement");
        criteria.select(root);
        criteria.where(builder.like(joinDepartment.get("nomDepartement"), nomDepartement));
        List<DemandeDm> demandes = session.createQuery(criteria).getResultList();
        List<String> demandesDm = new ArrayList<>();
        demandes.forEach(demande -> {
            JSONObject jsonDemande = new JSONObject();
            jsonDemande.put("demande", new JSONObject(demande));
            CriteriaQuery<DetailDemandeDm> criteriaQuery = builder.createQuery(DetailDemandeDm.class);
            Root<DetailDemandeDm> detailDemandeDmRoot = criteriaQuery.from(DetailDemandeDm.class);
            criteriaQuery.where(builder.equal(detailDemandeDmRoot.get("demande"), demande.getId()));
            List<DetailDemandeDm> detailsDemandeDm = session.createQuery(criteriaQuery).getResultList();
            jsonDemande.put("detailsDemande", detailsDemandeDm);
            demandesDm.add(jsonDemande.toString());
        });
        session.close();
        return demandesDm;
    }
    @Override
    public int ajouterDemandeDm(String nomDepartement){
        Session session = sessionFactory.openSession();
        Departement departement = departementRepository.findByName(nomDepartement);
        DemandeDm demandeDm = new DemandeDm(EtatDemande.INITIALISÉE, LocalDate.now(), departement);
        session.save(demandeDm);
        int idDemandeDm = demandeDm.getId();
        return idDemandeDm;
    }
    @Override
    public void ajouterDetailDemandeDM(String nomDm, int qte, int idDemande) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        DM dm = dmRepository.getDMByName(nomDm);
        DemandeDm demandeDm = session.find(DemandeDm.class, idDemande);
        DetailDemandeDm detailDemandeDm = new DetailDemandeDm(dm, qte, demandeDm);
        session.save(detailDemandeDm);

        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void updateEtatDemande(int id, String etatDemande) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DemandeDm demandeDm = session.find(DemandeDm.class, id);
        EtatDemande currentEtatDemande = demandeDm.getEtatDemande();
        if (currentEtatDemande.equals(EtatDemande.ACCEPTÉE) || currentEtatDemande.equals(EtatDemande.REJETÉE))
            return;
        if (currentEtatDemande.equals(EtatDemande.CONFIRMÉE) && etatDemande.equals(EtatDemande.INITIALISÉE))
            return;
        demandeDm.setEtatDemande(EtatDemande.fromString(etatDemande));
        demandeDm.setDate_fin(LocalDate.now());
        session.getTransaction().commit();
        session.close();
        }

    @Override
    public void updateEtatDetail(int idDetail, String etatDetail) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DetailDemandeDm detailDemandeDm = session.find(DetailDemandeDm.class, idDetail);
        detailDemandeDm.setEtatDetail(EtatDetail.fromString(etatDetail));
        session.getTransaction().commit();
        session.close();
    }
}
