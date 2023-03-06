package ma.uiass.eia.pds.persistance;


import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.dm.Dm;
import org.hibernate.Session;

import java.util.List;

public class EquipementRepositoryImpl  implements EquipementRepository {




   @Override
    public List<Dm> findAllLits() {

       Session session = GetSessionFactory.getSessionFactory().openSession();
        return session.createQuery("SELECT a FROM Dm a ", Dm.class).getResultList();

    }

    @Override
    public Dm findById(Long id) {

        Session session = GetSessionFactory.getSessionFactory().openSession();
        return null;
    }

    @Override
    public List<Dm> findLitByType(String title) {

        Session session = GetSessionFactory.getSessionFactory().openSession();
        return null;
    }


    @Override
    public void saveLit(Dm lit) {

        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(lit);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void deleteEquipement(Long b) {

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
