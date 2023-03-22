package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
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

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.like(departementJoin.get("nomDepartement"), nomDepartement));

        // Execute the query and store the result into lits
        List<LitItem> lits = session.createQuery(criteria).getResultList();
        System.out.println(lits);
        List<LitItem> litsSalle = new ArrayList<>();
        lits.forEach(elt -> {
            if (elt.getEspace().getClass() == Salle.class)
                litsSalle.add(elt);
        });

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

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.like(departementJoin.get("nomDepartement"), nomDepartement))
                .where(builder.equal(rootLit.get("occupied"), 0));

        // Execute the query and store the result into lits
        List<LitItem> lits = session.createQuery(criteria).getResultList();
        System.out.println(lits);
        List<LitItem> litsSalle = new ArrayList<>();
        lits.forEach(elt -> {
            if (elt.getEspace().getClass() == Salle.class)
                litsSalle.add(elt);
        });

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

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.like(departementJoin.get("nomDepartement"), nomDepartement))
                .where(builder.equal(rootLit.get("occupied"), 1));

        // Execute the query and store the result into lits
        List<LitItem> lits = session.createQuery(criteria).getResultList();
        System.out.println(lits);
        List<LitItem> litsSalle = new ArrayList<>();
        lits.forEach(elt -> {
            if (elt.getEspace().getClass() == Salle.class)
                litsSalle.add(elt);
        });

        // Close session
        session.close();

        return litsSalle;
    }
}
