package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.demande.DemandeDm;
import ma.uiass.eia.pds.model.demande.EtatDemande;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.dm.TypeDM;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class DemandeDmRepositoryImpl implements DemandeDmRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    DMRepository dmRepository = new DMRepositoryImpl();
    @Override
    public List<DemandeDm> getAllDemandes() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DemandeDm> criteria = builder.createQuery(DemandeDm.class);
        Root<DemandeDm> root = criteria.from(DemandeDm.class);
        criteria.select(root);
        List<DemandeDm> demandes = session.createQuery(criteria).getResultList();
        session.close();
        return demandes;
    }

    @Override
    public List<DemandeDm> getDemandesByDepartement(String nomDepartement) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DemandeDm> criteria = builder.createQuery(DemandeDm.class);
        Root<DemandeDm> root = criteria.from(DemandeDm.class);
        Join<DemandeDm, Departement> joinDepartment = root.join("departement");

        criteria.select(root);
        criteria.where(builder.equal(joinDepartment.get("nomDepartement"), nomDepartement));

        List<DemandeDm> demandes = session.createQuery(criteria).getResultList();
        session.close();
        return demandes;
    }

    @Override
    public void ajouterDemandeDM(String nomTypeDm, String nomDm, String nomDepartement, int qte) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Departement> criteria = criteriaBuilder.createQuery(Departement.class);
        Root<Departement> departement = criteria.from(Departement.class);

        criteria.select(departement);
        criteria.where(criteriaBuilder.like(departement.get("nomDepartement"), nomDepartement));
        Departement service = session.createQuery(criteria).getSingleResult();
        TypeDM typeDM = dmRepository.getTypeDmByName(nomTypeDm);
        DemandeDm demandeDm = new DemandeDm(qte, typeDM,nomDm,service);
        session.save(demandeDm);

        // Create Demande

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateEtatDemande(int id, String etatDemande) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DemandeDm demandeDm = session.find(DemandeDm.class, id);
        if (!etatDemande.equals("NONTRAITÉE")) {
            demandeDm.setEtatDemande(EtatDemande.fromString(etatDemande));
            demandeDm.setDate_fin(LocalDate.now());

        }
        session.getTransaction().commit();
        session.close();
    }
}
