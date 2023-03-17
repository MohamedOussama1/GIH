package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreRepositoryImpl implements ChambreRepository {
    SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    @Override
    public void saveChambre(String nomBatiment, double superficie, String nomChambre, String typeChambre) {

    }

    @Override
    public List<Chambre> getAllChambre() {
        return null;
    }

    @Override
    public List<Lit> getAllLitChambre(String nomDepartement) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Lit> and Lit.class indicates that the query is of return type Lit
        CriteriaQuery<Lit> criteria = builder.createQuery(Lit.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit


        Root<Lit> rootLit = criteria.from(Lit.class);
        Join<Lit, Espace> espaceJoin = rootLit.join("espace");
        Join<Espace, Departement> departementJoin = espaceJoin.join("departement");

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit);

        criteria.where(builder.like(departementJoin.get("nomDepartement"), nomDepartement));

        // Execute the query and store the result into lits
        List<Lit> lits = session.createQuery(criteria).getResultList();
        System.out.println(lits);
        List<Lit> litsChambre = new ArrayList<>();
        lits.forEach(elt -> {
            if (elt.getEspace().getClass() == Chambre.class)
                litsChambre.add(elt);
        });

        // Close session
        session.close();

        return litsChambre;


    }

    @Override
    public List<Lit> getAllOccupeLitChambre(String nomDepartement) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Lit> and Lit.class indicates that the query is of return type Lit
        CriteriaQuery<Lit> criteria = builder.createQuery(Lit.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit


        Root<Lit> rootLit = criteria.from(Lit.class);
        Join<Lit, Espace> espaceJoin = rootLit.join("espace");
        Join<Espace, Departement> departementJoin = espaceJoin.join("departement");

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit);

        criteria.where(builder.like(departementJoin.get("nomDepartement"), nomDepartement));
        criteria.where(builder.equal(rootLit.get("etat"), EtatLit.fromString("occupé")));

        // Execute the query and store the result into lits
        List<Lit> lits = session.createQuery(criteria).getResultList();
        System.out.println(lits);
        List<Lit> litsChambre = new ArrayList<>();
        lits.forEach(elt -> {
            if (elt.getEspace().getClass() == Chambre.class)
                litsChambre.add(elt);
        });

        // Close session
        session.close();

        return litsChambre;

    }

    @Override
    public List<Lit> getAllDisponibleLitChambre(String nomDepartement) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Lit> and Lit.class indicates that the query is of return type Lit
        CriteriaQuery<Lit> criteria = builder.createQuery(Lit.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit


        Root<Lit> rootLit = criteria.from(Lit.class);
        Join<Lit, Espace> espaceJoin = rootLit.join("espace");
        Join<Espace, Departement> departementJoin = espaceJoin.join("departement");

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit);

        criteria.where(builder.like(departementJoin.get("nomDepartement"), nomDepartement));
        criteria.where(builder.equal(rootLit.get("etat"), EtatLit.fromString("disponible")));

        // Execute the query and store the result into lits
        List<Lit> lits = session.createQuery(criteria).getResultList();
        System.out.println(lits);
        List<Lit> litsChambre = new ArrayList<>();
        lits.forEach(elt -> {
            if (elt.getEspace().getClass() == Chambre.class)
                litsChambre.add(elt);
        });

        // Close session
        session.close();

        return litsChambre;

    }
}
