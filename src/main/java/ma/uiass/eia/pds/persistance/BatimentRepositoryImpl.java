package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.batiment.Batiment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BatimentRepositoryImpl implements BatimentRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    private DepartementRepository departementRepository = new DepartementRepositoryImpl();
    @Override
    public void saveBatiment(String nomBatiment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Batiment(nomBatiment));
        session.getTransaction().commit();
        session.close();
    }
    public Batiment findByName(String nomBatiment){
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create query of return type Batiment
        CriteriaQuery<Batiment> criteria = builder.createQuery(Batiment.class);

        // Table or tables we want to select from
        Root<Batiment> root = criteria.from(Batiment.class);

        // Select *
        criteria.select(root);

        // Where batiment_nom = nomBatiment
        criteria.where(builder.equal(root.get("batiment_nom"), nomBatiment));

        // Execute query and store the result into batiments
        Batiment batiment = session.createQuery(criteria).getSingleResult();

        return batiment;

    }

    @Override
    public List<Batiment> getAllBatiment() {
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Batiment> and Batiment.class indicates that the query is of return type Batiment
        CriteriaQuery<Batiment> criteria = builder.createQuery(Batiment.class);

        // This line is equivalent to writing "FROM t_batiment" in the query, root contains columns of table t_batiment
        Root<Batiment> root = criteria.from(Batiment.class);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(root);

        // Execute the query and store the result into batiments
        List<Batiment> batiments = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return batiments;
    }

    @Override
    public void updateBatiment(String nomBatiment) {

    }

    @Override
    public void deleteBatiment(String nomBatiment) {

    }
}
