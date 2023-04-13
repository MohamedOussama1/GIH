package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.Dimensions;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.patient.Patient;
import ma.uiass.eia.pds.model.reservation.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class LitRepositoryImpl implements LitRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    @Override
    public List<LitItem> findAllLitStock() {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Lit> and Lit.class indicates that the query is of return type Lit
        CriteriaQuery<LitItem> criteria = builder.createQuery(LitItem.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit
        Root<LitItem> root = criteria.from(LitItem.class);
        Join<LitItem, Espace> espaceJoin = root.join("espace");

        // This line is equivalent to writing "Select * " in the query
        criteria.select(root)
                .where(builder.like(espaceJoin.get("nomEspace"), "Stock"));

        // Execute the query and store the result into lits
        List<LitItem> lits = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return lits;
    }
    @Override
    public int saveLit(TypeLit type, ModelLit modelLit, Dimensions dimensions, double chargeMax, Period garantie, double prix, Set<FonctionLit> fonctionsLit, String frontColor,  String description) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Lit litDescription = new Lit(type, modelLit, dimensions, chargeMax, garantie, prix, fonctionsLit, frontColor, description);
        session.save(litDescription);
        int id = litDescription.getNumero();
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public void saveManyLit(int quantity, int litDescriptionId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        IntStream.range(0, quantity).forEach( i -> {
            Lit lit = session.find(Lit.class, litDescriptionId);
            LitItem litItem = new LitItem(lit);
            Espace stock = session.find(Espace.class, 21);
            litItem.setEspace(stock);
            litItem.setCode(String.valueOf((int)(Math.random() * 10000)));
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
        int numType = typeEspace.equals("Salle") ? 1 : 2;

        Predicate predicate1 = criteriaBuilder.like(departementJoin.get("nomDepartement"), nomDepartement);
        Predicate predicate2 = criteriaBuilder.equal(rootEspace.get("numero"), numEspace);
        Predicate predicate3 = criteriaBuilder.equal(rootEspace.type(), numType);
        Predicate predicate4 = criteriaBuilder.isNull(rootEspace.get("departement"));
        criteria.select(rootEspace.get("id"))
                .where(criteriaBuilder.and(predicate1, predicate2, criteriaBuilder.or(predicate3, predicate4)));
        criteria.select(rootEspace.get("id"))
                .where(criteriaBuilder.and(predicate2, criteriaBuilder.or(predicate3, predicate4)));

        Espace espace = session.find(Espace.class, session.createQuery(criteria).getSingleResult());
        LitItem lit = session.find(LitItem.class, idLit);
        lit.setEspace(espace);
        session.getTransaction().commit();
        session.close();
    }
}
