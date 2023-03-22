package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.LitEquipe;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.patient.Patient;
import ma.uiass.eia.pds.model.reservation.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.IntStream;

public class LitRepositoryImpl implements LitRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    @Override
    public List<LitItem> findAllLit(String nomDepartement) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Lit> and Lit.class indicates that the query is of return type Lit
        CriteriaQuery<LitItem> criteria = builder.createQuery(LitItem.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit
        Root<LitItem> root = criteria.from(LitItem.class);
        Join<LitItem, Espace> espaceJoin = root.join("espace");
        Join<Espace, Departement> departementJoin = espaceJoin.join("departement");

        // This line is equivalent to writing "Select * " in the query
        criteria.select(root)
                .where(builder.like(departementJoin.get("nomDepartement"), nomDepartement));

        // Execute the query and store the result into lits
        List<LitItem> lits = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return lits;
    }
    @Override
    public void saveLit(TypeLit type, ModelLit modelLit, String dimensions, double chargeMax, Period garantie, double prix, String description) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Lit(type, modelLit, dimensions, chargeMax, garantie, prix,  description));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveManyLit(int quantity, int litDescriptionId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        IntStream.range(0, quantity).forEach( i -> {
            Lit lit = session.find(Lit.class, litDescriptionId);
            LitItem litItem = new LitItem(lit);
            session.save(litItem);
            });
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void occuperLit(int idLit, int idPatient, LocalDateTime dateReservation, LocalDateTime dateDebut, LocalDateTime dateFin) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        LitItem lit = session.find(LitItem.class, idLit);
        Patient patient = session.find(Patient.class, idPatient);
        Reservation reservation = new Reservation(dateReservation, dateDebut, dateFin, lit, patient);
        session.save(reservation);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteLit(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.find(LitItem.class, id));
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void deplacerLit(String nomDepartement, String typeEspace, int numEspace, int  idLit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Espace> criteria = criteriaBuilder.createQuery(Espace.class);
        Root<Espace> rootEspace = criteria.from(Espace.class);
        Join<Espace, Departement> departementJoin = rootEspace.join("departement");

        // Check if type is Salle or Chambre
        int numType = typeEspace == "Salle" ? 1 : 2;

        criteria.select(rootEspace.get("id"))
                .where(criteriaBuilder.like(departementJoin.get("nomDepartement"), nomDepartement))
                .where(criteriaBuilder.equal(rootEspace.get("numero"), numEspace))
                .where(criteriaBuilder.equal(rootEspace.type(), numType));

        Espace espace = session.createQuery(criteria).getSingleResult();
        LitItem lit = session.find(LitItem.class, idLit);
        lit.setEspace(espace);
        session.getTransaction().commit();
        session.close();
    }
}
