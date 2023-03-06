package ma.uiass.eia.pds.persistance;


import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.departement.Departement;
import org.hibernate.Session;

import java.util.List;

public class DepartmentRepositoryImpl implements DepartmentRpository{


    @Override

    public List<Departement> findAllLits() {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.close();
        return session.createQuery("SELECT a FROM Departement a ", Departement.class).getResultList();

    }
    @Override
    public void saveLit(Departement lit) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(lit);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Departement findById(Long id) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.close();

        return null;
    }

    @Override
    public List<Departement> findLitByType(String title) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.close();
        return null;
    }


    @Override
    public void deleteDepatement(Long b) {
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
