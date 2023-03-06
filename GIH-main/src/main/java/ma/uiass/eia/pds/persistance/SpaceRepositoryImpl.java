package ma.uiass.eia.pds.persistance;


import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.espace.Espace;

import org.hibernate.Session;

import java.util.List;

public class SpaceRepositoryImpl implements SpaceRepository{

    @Override

    public List<Espace> findAllLits() {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        return session.createQuery("SELECT a FROM Espace a ", Espace.class).getResultList();

    }
    @Override
    public void saveLit(Espace lit) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(lit);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public Espace findById(Long id) {
        Session session = GetSessionFactory.getSessionFactory().openSession();

        return null;
    }

    @Override
    public List<Espace> findLitByType(String title) {
        Session session = GetSessionFactory.getSessionFactory().openSession();

        return null;
    }


    @Override
    public void deleteSpce(Long b) {
        Session session = GetSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();
        Object obj = session.load(Lit.class, b);
        session.delete(obj);
        session.getTransaction().commit();

        session.close();

    }

    @Override
    public void update(Long b) {
        Session session = GetSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();
        session.update(b);
        session.getTransaction().commit();

        session.close();

    }
}
