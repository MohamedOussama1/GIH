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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<Chambre, List<LitItem>> getAllLitChambre(String nomDepartement) {
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
        Predicate predicate2 = builder.equal(espaceJoin.type(), 2);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.and(predicate1, predicate2));

        // Execute the query and store the result into lits
        List<LitItem> litsChambre = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return convertBedList(litsChambre);


    }

    @Override
    public Map<Chambre, List<LitItem>> getAllOccupeLitChambre(String nomDepartement) {
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
        Predicate predicate2 = builder.equal(espaceJoin.type(), 2);
        Predicate predicate3 = builder.equal(rootLit.get("occupied"), 1);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.and(predicate1, predicate2, predicate3));

        // Execute the query and store the result into lits
        List<LitItem> litsChambre = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return convertBedList(litsChambre);

    }

    @Override
    public Map<Chambre, List<LitItem>> getAllDisponibleLitChambre(String nomDepartement) {
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
        Predicate predicate2 = builder.equal(espaceJoin.type(), 2);
        Predicate predicate3 = builder.equal(rootLit.get("occupied"), 0);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.and(predicate1, predicate2, predicate3));

        // Execute the query and store the result into lits
        List<LitItem> litsChambre = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return convertBedList(litsChambre);

    }

    // Helper method to convert bed List to Map of (Espace : List of LitItems inside)
    public Map<Chambre, List<LitItem>> convertBedList(List<LitItem> litItems){
        Map<Chambre, List<LitItem>> espaceListMap = new HashMap<>();
        litItems.forEach(elt -> {
            Chambre espace = (Chambre) elt.getEspace();
            if (espaceListMap.containsKey(espace))
                espaceListMap.get(espace).add(elt);
            else {
                espaceListMap.put(espace, new ArrayList<>());
                espaceListMap.get(espace).add(elt);
            }
        });
        return espaceListMap;
    }
}
