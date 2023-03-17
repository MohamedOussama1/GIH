package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.departement.NomDepartement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DepartementRepositoryImpl implements DepartementRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();

    @Override
    public void save(String nomDepartement) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Departement(nomDepartement, 0));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Departement findByName(String nomDepartement) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create query of return type Departement
        CriteriaQuery<Departement> criteria = builder.createQuery(Departement.class);

        // Table or tables we want to select from
        Root<Departement> root = criteria.from(Departement.class);

        // Select *
        criteria.select(root);

        // Where nomDepartement = nomDepartement. Note that "nomDepartement" column is stored as Enum not String
        criteria.where(builder.equal(root.get("nomDepartement"), NomDepartement.fromString(nomDepartement)));

        // Execute query and store the result into departements
        Departement departement = session.createQuery(criteria).getSingleResult();

        return departement;
    }

    @Override
    public List<Departement> findAllDepartement() {
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Departement> and Departement.class indicates that the query is of return type Departement
        CriteriaQuery<Departement> criteria = builder.createQuery(Departement.class);

        // This line is equivalent to writing "FROM t_departement" in the query, root contains columns of table t_departement
        Root<Departement> root = criteria.from(Departement.class);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(root);

        // Execute the query and store the result into departements
        List<Departement> departements = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return departements;

    }
}
