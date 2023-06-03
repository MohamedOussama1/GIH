package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;
import ma.uiass.eia.pds.model.espace.salle.TypeSalle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;

import javax.persistence.criteria.*;
import java.util.*;

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
    public List<String> getAllLitSalle(String nomDepartement) {
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
        Predicate predicate3 = builder.notEqual(espaceJoin.get("numero"), 1001);
        Predicate predicate4 = builder.notEqual(espaceJoin.get("numero"), 1002);
        Predicate predicate5 = builder.notEqual(espaceJoin.get("numero"), 1003);
        Predicate predicate6 = builder.notEqual(espaceJoin.get("numero"), 1004);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.and(predicate1, predicate2, predicate3, predicate4, predicate5, predicate6));

        // Execute the query and store the result into lits
        List<LitItem> litsSalle = session.createQuery(criteria).getResultList();

        // Convert to Map of Salle:Lits
        Map<Salle, List<LitItem>> litsSalleMap = convertBedList(litsSalle);

        // Convert to Json
        List<String> litsSalleJson = new ArrayList<>();
        populateJsonListSalle(litsSalleJson, litsSalleMap);

        // Close session
        session.close();

        return litsSalleJson;
    }



    @Override
    public List<String> getAllDisponibleLitSalle(String nomDepartement) {
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
        Predicate predicate7 = builder.equal(rootLit.get("occupied"), 0);
        Predicate predicate3 = builder.notEqual(espaceJoin.get("numero"), 1001);
        Predicate predicate4 = builder.notEqual(espaceJoin.get("numero"), 1002);
        Predicate predicate5 = builder.notEqual(espaceJoin.get("numero"), 1003);
        Predicate predicate6 = builder.notEqual(espaceJoin.get("numero"), 1004);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.and(predicate1, predicate2, predicate3, predicate4, predicate5, predicate6, predicate7));

        // Execute the query and store the result into lits
        List<LitItem> litsSalle = session.createQuery(criteria).getResultList();


        // Convert to Json
        List<String> litsSalleJson = new ArrayList<>();
        litsSalle.forEach(litSalle -> litsSalleJson.add(litSalle.toString()));

        // Close session
        session.close();

        return litsSalleJson;
    }

    @Override
    public List<Salle> getAll_Salle(String nomDepartement) {

        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <LitItem> and LitItem.class indicates that the query is of return type LitItem
        CriteriaQuery<Salle> criteria = builder.createQuery(Salle.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit
        Root<Salle> root = criteria.from(Salle.class);

        Join<Salle,Departement> espaceDep=root.join("departement");

        // Predicate predicate1=builder.equal(root.type(),Chambre.class);

        Predicate predicate2=builder.like(espaceDep.get("nomDepartement"), nomDepartement);
        criteria.select(root).where(builder.and(predicate2));

        List<Salle> lstespace=session.createQuery(criteria).getResultList();


        return lstespace;
    }

    @Override
    public List<String> getAllOccupeLitSalle(String nomDepartement) {
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
        Predicate predicate7 = builder.equal(rootLit.get("occupied"), 1);
        Predicate predicate3 = builder.notEqual(espaceJoin.get("numero"), 1001);
        Predicate predicate4 = builder.notEqual(espaceJoin.get("numero"), 1002);
        Predicate predicate5 = builder.notEqual(espaceJoin.get("numero"), 1003);
        Predicate predicate6 = builder.notEqual(espaceJoin.get("numero"), 1004);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(rootLit)
                .where(builder.and(predicate1, predicate2, predicate3, predicate4, predicate5, predicate6, predicate7));

        // Execute the query and store the result into lits
        List<LitItem> litsSalle = session.createQuery(criteria).getResultList();


        // Convert to Json
        List<String> litsSalleJson = new ArrayList<>();
        litsSalle.forEach(litSalle -> litsSalleJson.add(litSalle.toString()));

        // Close session
        session.close();

        return litsSalleJson;
    }
    public Map<Salle, List<LitItem>> convertBedList(List<LitItem> litItems){
        Map<Salle, List<LitItem>> espaceListMap = new HashMap<>();
        litItems.forEach(elt -> {
            Salle espace = (Salle) elt.getEspace();
            if (espaceListMap.containsKey(espace))
                espaceListMap.get(espace).add(elt);
            else {
                espaceListMap.put(espace, new ArrayList<>());
                espaceListMap.get(espace).add(elt);
            }
        });
        return espaceListMap;
    }
    public void populateJsonListSalle(List<String> jsonList, Map<Salle, List<LitItem>> espaceBedMap) {
        espaceBedMap.forEach((espace, litLst) -> {
            JSONObject espaceJson = new JSONObject();
            espaceJson.put("salle", new JSONObject(espace));
            espaceJson.put("litLst", litLst);
            jsonList.add(espaceJson.toString());
        });
    }

}
