package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.EtatLit;
import ma.uiass.eia.pds.model.Lit;
import ma.uiass.eia.pds.model.TypeLit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LitRepositoryImpl implements LitRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    @Override
    public List<Lit> findAllLit() {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Lit> and Lit.class indicates that the query is of return type is Lit
        CriteriaQuery<Lit> criteria = builder.createQuery(Lit.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit
        Root<Lit> root = criteria.from(Lit.class);

        // This line is equivalent to writing "Select * " in the query
        criteria.select(root);

        // Execute the query and store the result into lits
        List<Lit> lits = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return lits;
    }
    @Override
    public void saveLit(Lit lit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(lit);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateLit(int id, EtatLit etatLit, TypeLit typeLit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Lit lit = session.find(Lit.class, id);
        lit.setEtat(etatLit);
        lit.setType(typeLit);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public List<Lit> findLitByEtat(String etatLit) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create query of return type Lit
        CriteriaQuery<Lit> criteria = builder.createQuery(Lit.class);

        // Table or tables we want to select from
        Root<Lit> root = criteria.from(Lit.class);

        // Select *
        criteria.select(root);

        // Where etat = etatLit. Note that "etat" column is stored as Enum not String
        criteria.where(builder.equal(root.get("etat"), EtatLit.fromString(etatLit)));

        // Execute query and store the result into lits
        List<Lit> lits = session.createQuery(criteria).getResultList();

        return lits;
    }


    @Override
    public void deleteLit(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.find(Lit.class, id));
        session.getTransaction().commit();
        session.close();
    }
}
