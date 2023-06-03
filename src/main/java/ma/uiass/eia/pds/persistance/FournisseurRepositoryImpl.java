package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.fournisseur.Fournisseur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.List;

public class FournisseurRepositoryImpl implements FournisseurRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    @Override
    public List<Fournisseur> getAll() {
        // Open a new session
        Session session = sessionFactory.openSession();

        // Create Criteria Builder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create Criteria Query
        CriteriaQuery<Fournisseur> query = builder.createQuery(Fournisseur.class);

        // Select all records from the table
        Root<Fournisseur> root = query.from(Fournisseur.class);
        query.select(root);

        // Execute the query and fetch the results
        List<Fournisseur> result = session.createQuery(query).getResultList();

        // Close the session
        session.close();

        return result;
    }

    @Override
    public Fournisseur getFournisseurByName(String fournisseur) {
        System.out.println(fournisseur);
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create query of return type Departement
        CriteriaQuery<Fournisseur> criteria = builder.createQuery(Fournisseur.class);

        // Table or tables we want to select from
        Root<Fournisseur> root = criteria.from(Fournisseur.class);

        // Select *
        criteria.select(root);

        // Where nomDepartement = nomDepartement. Note that "nomDepartement" column is stored as Enum not String
        criteria.where(builder.like(root.get("nom"),fournisseur));

        // Execute query and store the result into departements
        Fournisseur fr = session.createQuery(criteria).getSingleResult();
        return fr;
    }

    @Override
    public void saveFournisseur(String nom, String ville, String email, String codeAPE, String formeJuridique, String numSiren, String tel) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Fournisseur fournisseur = new Fournisseur(nom,ville,email,codeAPE,formeJuridique,numSiren,tel);
            session.save(fournisseur);
            session.getTransaction().commit();
            session.close();

    }


    @Override
    public int deleteFournisseur( int id) {
        int deleted = 1;
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Fournisseur> criteria = builder.createCriteriaDelete(Fournisseur.class);
        Root<Fournisseur> root = criteria.from(Fournisseur.class);
        criteria.where(builder.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        try {
            session.createQuery(criteria).executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            deleted = 0;
        }finally {
            session.close();
        }
        return deleted;
    }

    @Override
    public void modifyFournisseur(int id,String nom, String ville, String email, String codeAPE, String formeJuridique, String numSiren, String tel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<Fournisseur> update = cb.createCriteriaUpdate(Fournisseur.class);
        Root<Fournisseur> root = update.from(Fournisseur.class);
        update.set(root.get("nom"), nom);
        update.set(root.get("email"), email);
        update.set(root.get("ville"), ville);
        update.set(root.get("codeAPE"), codeAPE);
        update.set(root.get("formeJuridique"), formeJuridique);
        update.set(root.get("numSiren"), numSiren);
        update.set(root.get("tel"),tel);
        update.where(cb.equal(root.get("id"), id));

        session.createQuery(update).executeUpdate();
        transaction.commit();
        session.close();
    }
//public static void main(String [] args){
//        FournisseurRepository fr = new FournisseurRepositoryImpl();
//
//        fr.modifyFournisseur(1,"youness","rabat","k","655","k","6","6");
//
//}


}
