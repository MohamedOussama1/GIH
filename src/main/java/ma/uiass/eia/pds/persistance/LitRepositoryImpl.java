package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class LitRepositoryImpl implements LitRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    private Session session = sessionFactory.openSession();
    @Override
    public List<Lit> findAllLits() {
        return session.createQuery("SELECT a FROM Lit a ", Lit.class).getResultList();

    }
    @Override
    public void saveLit(Lit lit) {
        session.beginTransaction();
        session.save(lit);
        session.getTransaction().commit();
    }

    @Override
    public Lit findById(Long id) {
        return null;
    }

    @Override
    public List<Lit> findLitByType(String title) {
        return null;
    }


    @Override
    public void deleteLit(Lit b) {

    }
}
