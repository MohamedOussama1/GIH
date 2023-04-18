package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.DMItem;
import ma.uiass.eia.pds.model.dm.TypeDM;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Random;

public class DMRepositoryImpl implements DMRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();

    public DMRepositoryImpl() {

    }

   /* @Override
    public void saveDM(String nom, int quantité, TypeDM typeDM) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DM dm = new DM(nom,quantité,typeDM);
        session.save(dm);
        session.getTransaction().commit();
        session.close();
    }

    */
   @Override
   public void saveDM(String nom, int quantité, TypeDM typeDM) {
       Session session = sessionFactory.openSession();
       session.beginTransaction();
       DM dm = new DM(nom, quantité, typeDM);
       session.save(dm);

       if (typeDM.getNomType().equalsIgnoreCase("Mobiler") ||
               typeDM.getNomType().equalsIgnoreCase("équipement léger") ||
               typeDM.getNomType().equalsIgnoreCase("équipement lourd")) {

           for (int i = 0; i < quantité; i++) {
               // Generate a 3-digit number
               int code = new Random().nextInt(1000);

               // Create the DMItem
               String codeNOM = nom + String.format("%03d", code);
               DMItem dmItem = new DMItem(codeNOM, dm);
               session.save(dmItem);
               session.flush(); // Manually flush the session
           }
       }

       session.getTransaction().commit();
       session.close();
   }






    @Override
    public void saveTypeDM(String nom) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TypeDM typedm = new TypeDM(nom);
        session.save(typedm);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public DM getAllDM() {
        return null;
    }

    @Override
    public List<TypeDM> getAllTypeDM() {
        // Open a new session
        Session session = sessionFactory.openSession();

        // Create Criteria Builder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create Criteria Query
        CriteriaQuery<TypeDM> query = builder.createQuery(TypeDM.class);

        // Select all records from the table
        Root<TypeDM> root = query.from(TypeDM.class);
        query.select(root);

        // Execute the query and fetch the results
        List<TypeDM> result = session.createQuery(query).getResultList();

        // Close the session
        session.close();

        return result;
    }

    @Override
    public List<DM> getAllDMByType(String typeDM) {
        // Open a new session
        Session session = sessionFactory.openSession();

        // Create Criteria Builder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create Criteria Query
        CriteriaQuery<DM> query = builder.createQuery(DM.class);

        // Select all records from the table
        Root<DM> root = query.from(DM.class);
        Join<DM, TypeDM> join = root.join("typeDM");
        query.select(root);
        query.where(builder.equal(join.get("nomType"), typeDM));

        // Execute the query and fetch the results
        List<DM> result = session.createQuery(query).getResultList();

        // Close the session
        session.close();

        return result;
    }


    @Override
    public TypeDM getTypeDmByName(String typenom) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create query of return type Departement
        CriteriaQuery<TypeDM> criteria = builder.createQuery(TypeDM.class);

        // Table or tables we want to select from
        Root<TypeDM> root = criteria.from(TypeDM.class);

        // Select *
        criteria.select(root);

        // Where nomDepartement = nomDepartement. Note that "nomDepartement" column is stored as Enum not String
        criteria.where(builder.equal(root.get("nomType"),typenom));

        // Execute query and store the result into departements
        TypeDM typeDM = session.createQuery(criteria).getSingleResult();

        return typeDM;
    }
   /* public static void main(String[] args) throws IOException {
        DMRepository DMR = new DMRepositoryImpl();
        List<DM> typeDMs = DMR.getAllDMByType("Mobilier");
        System.out.println(typeDMs);

       // DMR.saveTypeDM("mobilier");
    }
*/





}
