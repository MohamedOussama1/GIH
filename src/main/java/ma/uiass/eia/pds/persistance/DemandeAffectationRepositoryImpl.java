package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.demande.DemandeAffectation;
import ma.uiass.eia.pds.model.demande.EtatDemande;
import ma.uiass.eia.pds.model.departement.Departement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class DemandeAffectationRepositoryImpl implements DemandeAffectationRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();

    public DemandeAffectationRepositoryImpl() {
    }

    @Override
    public List<DemandeAffectation> getAllDemandes() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DemandeAffectation> criteria = builder.createQuery(DemandeAffectation.class);
        Root<DemandeAffectation> root = criteria.from(DemandeAffectation.class);
        criteria.select(root);
        List<DemandeAffectation> demandes = session.createQuery(criteria).getResultList();
        session.close();
        return demandes;
    }

    @Override
    public List<DemandeAffectation> getDemandesByDepartement(String nomDepartement) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DemandeAffectation> criteria = builder.createQuery(DemandeAffectation.class);
        Root<DemandeAffectation> root = criteria.from(DemandeAffectation.class);
        Join<DemandeAffectation, Departement> joinDepartment = root.join("departement");

        criteria.select(root);
        criteria.where(builder.equal(joinDepartment.get("nomDepartement"), nomDepartement));

        List<DemandeAffectation> demandes = session.createQuery(criteria).getResultList();
        session.close();
        return demandes;
    }


    @Override
    public void ajouterDemandeAffectation(TypeLit typeLit, ModelLit modelLit, String nomDepartement,  int qte){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Departement> criteria = criteriaBuilder.createQuery(Departement.class);
        Root<Departement> departement = criteria.from(Departement.class);

        criteria.select(departement);
        criteria.where(criteriaBuilder.like(departement.get("nomDepartement"), nomDepartement));
        Departement service = session.createQuery(criteria).getSingleResult();

        DemandeAffectation d = new DemandeAffectation(typeLit,modelLit,service,qte);
        session.save(d);

        // Create Demande

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateEtatDemande(int id, EtatDemande etatDemande) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DemandeAffectation demandeAffectation = session.find(DemandeAffectation.class, id);
        if (etatDemande != EtatDemande.NONTRAITÉE ) {
            demandeAffectation.setEtatDemande(etatDemande);
            demandeAffectation.setDate_fin(LocalDate.now());

        }
        session.getTransaction().commit();
        session.close();
    }

    /*public static void main(String[] args) throws IOException {
        DemandeAffectationRepository DAR = new DemandeAffectationRepositoryImpl();
        System.out.println(DAR.getDemandesByDepartement("Cardiologie"));
        //DAR.ajouterDemandeAffectation(TypeLit.ELECTRIQUE,ModelLit.CIRCULATION,"Cardiologie",12);
        //DAR.updateEtatDemande(1,EtatDemande.REJETÉE);
 }


     */









   }
