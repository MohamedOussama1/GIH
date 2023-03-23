package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.salle.Salle;
import ma.uiass.eia.pds.model.espace.salle.TypeSalle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class SalleRepositoryImpl implements SalleRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    private DepartementRepository departementRepository = new DepartementRepositoryImpl();
    public void saveSalle(String nomDepartement, int numero, double superficie, String nomSalle, String typeSalle) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Salle(nomSalle, numero, superficie, departementRepository.findByName(nomDepartement), TypeSalle.fromString(typeSalle)) {
        });
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public List<LitItem> getAllLitSalle(String nomDepartement) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <LitItem> and LitItem.class indicates that the query is of return type LitItem
        CriteriaQuery<LitItem> criteria = builder.createQuery(LitItem.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit


        Root<LitItem> rootLit = criteria.from(LitItem.class);
        Join<LitItem, Espace> espaceJoin = rootLit.join("espace");
        Join<Espace, Departement> departementJoin = espaceJoin.join("departement");


        Predicate predicate1 = builder.like(departementJoin.get("nomDepartement"), nomDepartement);
        Predicate predicate2 = builder.equal(espaceJoin.type(), 1);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.and(predicate1, predicate2));

        // Execute the query and store the result into lits
        List<LitItem> litsSalle = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return litsSalle;


    }



    @Override
    public List<LitItem> getAllDisponibleLitSalle(String nomDepartement) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <LitItem> and LitItem.class indicates that the query is of return type LitItem
        CriteriaQuery<LitItem> criteria = builder.createQuery(LitItem.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit


        Root<LitItem> rootLit = criteria.from(LitItem.class);
        Join<LitItem, Espace> espaceJoin = rootLit.join("espace");
        Join<Espace, Departement> departementJoin = espaceJoin.join("departement");

        Predicate predicate1 = builder.like(departementJoin.get("nomDepartement"), nomDepartement);
        Predicate predicate2 = builder.equal(espaceJoin.type(), 1);
        Predicate predicate3 = builder.equal(rootLit.get("occupied"), 0);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.and(predicate1, predicate2, predicate3));

        // Execute the query and store the result into lits
        List<LitItem> litsSalle = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return litsSalle;

    }

    @Override
    public List<LitItem> getAllOccupeLitSalle(String nomDepartement) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Lit> and LitItem.class indicates that the query is of return type LitItem
        CriteriaQuery<LitItem> criteria = builder.createQuery(LitItem.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit


        Root<LitItem> rootLit = criteria.from(LitItem.class);
        Join<LitItem, Espace> espaceJoin = rootLit.join("espace");
        Join<Espace, Departement> departementJoin = espaceJoin.join("departement");

        Predicate predicate1 = builder.like(departementJoin.get("nomDepartement"), nomDepartement);
        Predicate predicate2 = builder.equal(espaceJoin.type(), 1);
        Predicate predicate3 = builder.equal(rootLit.get("occupied"), 1);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.and(predicate1, predicate2, predicate3));

        // Execute the query and store the result into lits
        List<LitItem> litsSalle = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return litsSalle;
    }
}
