package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.chambre.TypeChambre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreRepositoryImpl implements ChambreRepository {
    DepartementRepository departementRepository = new DepartementRepositoryImpl();
    SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    @Override
    public void saveChambre(String nomDepartement, int numero, double superficie, String nomChambre, String typeChambre) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Chambre(nomChambre, numero, superficie, departementRepository.findByName(nomDepartement), TypeChambre.fromString(typeChambre)) {
        });
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Chambre> getAllChambre() {
        return null;
    }

    @Override
    public List<LitItem> getAllLitChambre(String nomDepartement) {
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

        // This line is equivalent to writing "Select * " and "Where ..." in the query
        criteria.select(rootLit)
                .where(builder.like(departementJoin.get("nomDepartement"), nomDepartement));

        // Execute the query and store the result into lits
        List<LitItem> lits = session.createQuery(criteria).getResultList();
        System.out.println(lits);
        List<LitItem> litsChambre = new ArrayList<>();
        lits.forEach(elt -> {
            if (elt.getEspace().getClass() == Chambre.class)
                litsChambre.add(elt);
        });

        // Close session
        session.close();

        return litsChambre;


    }

    @Override
    public List<LitItem> getAllOccupeLitChambre(String nomDepartement) {
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

        // This line is equivalent to writing "Select * " and "Where ..." in the query
        criteria.select(rootLit)
                .where(builder.like(departementJoin.get("nomDepartement"), nomDepartement))
                .where(builder.equal(rootLit.get("occupied"), 1));

        // Execute the query and store the result into lits
        List<LitItem> lits = session.createQuery(criteria).getResultList();
        System.out.println(lits);
        List<LitItem> litsChambre = new ArrayList<>();
        lits.forEach(elt -> {
            if (elt.getEspace().getClass() == Chambre.class)
                litsChambre.add(elt);
        });

        // Close session
        session.close();

        return litsChambre;

    }

    @Override
    public List<LitItem> getAllDisponibleLitChambre(String nomDepartement) {
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

        // This line is equivalent to writing "Select *" and "Where ..." in the query
        criteria.select(rootLit)
                .where(builder.like(departementJoin.get("nomDepartement"), nomDepartement))
                .where(builder.equal(rootLit.get("occupied"), 0));

        // Execute the query and store the result into lits
        List<LitItem> lits = session.createQuery(criteria).getResultList();
        System.out.println(lits);
        List<LitItem> litsChambre = new ArrayList<>();
        lits.forEach(elt -> {
            if (elt.getEspace().getClass() == Chambre.class)
                litsChambre.add(elt);
        });

        // Close session
        session.close();

        return litsChambre;

    }
}
