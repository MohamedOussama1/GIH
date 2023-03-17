package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
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
    public List<Lit> findAllLit(String nomDepartement) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Lit> and Lit.class indicates that the query is of return type Lit
        CriteriaQuery<Lit> criteria = builder.createQuery(Lit.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit
        Root<Lit> root = criteria.from(Lit.class);
        Join<Lit, Espace> espaceJoin = root.join("espace");
        Join<Espace, Departement> departementJoin = espaceJoin.join("departement");

        // This line is equivalent to writing "Select * " in the query
        criteria.select(root);
        criteria.where(builder.like(departementJoin.get("nomDepartement"), nomDepartement));

        // Execute the query and store the result into lits
        List<Lit> lits = session.createQuery(criteria).getResultList();

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
    public void updateLit(int id, EtatLit etatLit ) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Lit lit = session.find(Lit.class, id);
        lit.setEtat(etatLit);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void occuperLit(int id, LocalDateTime dateDebut, LocalDateTime dateFin) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Lit lit = session.find(Lit.class, id);
        Reservation reservation = new Reservation(dateDebut, dateFin, lit);
        lit.setReservation(reservation);
        session.save(reservation);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteLit(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.find(Lit.class, id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteManyLits(List<Integer> idLitList) {

    }
}
