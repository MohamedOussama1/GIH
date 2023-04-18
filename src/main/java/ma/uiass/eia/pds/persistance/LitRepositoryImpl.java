package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.Dimensions;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
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
    public List<String> findAllLitStock() {
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

        // Convert to Json
        List<String> litsJson = new ArrayList<>();
        lits.forEach(lit -> {
                    JSONObject jsonLit = new JSONObject(lit);
                    litsJson.add(jsonLit.toString());
                });

        // Close session
        session.close();

        return litsJson;
    }


    @Override
    public int saveLit(String type, String modelLit, String dimensions, double chargeMax, Period garantie, double prix, List<String> fonctions, String frontColor,  String description) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Convert List<String> fonctions to Set<FonctionLit> fonctions
        Set<FonctionLit> fonctionsLit = new HashSet<>();
        fonctions.forEach(fonction -> fonctionsLit.add(FonctionLit.valueOf(fonction)));

        // Extract longeur, largeur, hauteur from String dimensions as List<Double>
        List<Double> dimensionsValues = Arrays.stream(dimensions.split("x")).map(Double::valueOf).collect(Collectors.toList());
        Dimensions dimensionsLit = new Dimensions(dimensionsValues.get(0), dimensionsValues.get(1), dimensionsValues.get(2));

        Lit litDescription = new Lit(TypeLit.valueOf(type), ModelLit.valueOf(modelLit), dimensionsLit, chargeMax, garantie, prix, fonctionsLit, frontColor, description);
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
            Espace stock = session.find(Espace.class, 33);
            litItem.setEspace(stock);
            litItem.setCode(String.valueOf((int)(Math.random() * 10000)));
            session.save(litItem);
            });
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void occuperLit(int idLit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        LitItem lit = session.find(LitItem.class, idLit);

        if(lit.getOccupied()) {
            lit.setOccupied(false);
        }
        else {
            lit.setOccupied(true);
        }
        session.update(lit);
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

    @Override
    public List<String> getFonctionsLit() {
        return Arrays.stream(FonctionLit.values()).map(fonctionLit-> fonctionLit.name()).collect(Collectors.toList());
    }

    @Override
    public List<String> getTypesLit() {
        return Arrays.stream(TypeLit.values()).map(typeLit-> typeLit.name()).collect(Collectors.toList());
    }

    @Override
    public List<String> getModelsLit() {
        return Arrays.stream(ModelLit.values()).map(modelLit -> modelLit.name()).collect(Collectors.toList());
    }

}
