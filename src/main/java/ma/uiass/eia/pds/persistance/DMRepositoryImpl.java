package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.DMItem;
import ma.uiass.eia.pds.model.dm.TypeDM;
import ma.uiass.eia.pds.model.espace.Espace;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Random;

public class DMRepositoryImpl implements DMRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();



   @Override
   public void saveDM(String nom, int quantité, TypeDM typeDM,String image_path) {
       Session session = sessionFactory.openSession();
       session.beginTransaction();
       DM dm = new DM(nom, quantité, typeDM,image_path);
       session.save(dm);

//       if (typeDM.getNomType().equalsIgnoreCase("Mobiler") ||
//               typeDM.getNomType().equalsIgnoreCase("équipement léger") ||
//               typeDM.getNomType().equalsIgnoreCase("équipement lourd")) {

           for (int i = 0; i < quantité; i++) {
               // Generate a 3-digit number
               int code = new Random().nextInt(1000);

               // Create the DMItem
               String codeNOM = nom + String.format("%03d", code);
               DMItem dmItem = new DMItem(codeNOM, dm);
               session.save(dmItem);
               session.flush(); // Manually flush the session
           }
      // }

       session.getTransaction().commit();
       session.close();
   }

   @Override
   public void saveTypeDM(String nom, String Categorie) {
       Session session = sessionFactory.openSession();
       session.beginTransaction();
       if(getAllTypeDM() !=null) {
           List<TypeDM> allTypeDM = getAllTypeDM();
           boolean typeDMExists = allTypeDM.stream().anyMatch(t -> t.getNomType().equals(nom));
           if (!typeDMExists) {
               TypeDM typedm = new TypeDM(nom,Categorie);
               session.save(typedm);
           }
           session.getTransaction().commit();
           session.close();
       }
       else{

           TypeDM typedm = new TypeDM(nom,Categorie);
            session.save(typedm);

            session.getTransaction().commit();
            session.close();

       }
   }

    @Override
    public List<DM> getAllDM() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DM> query = builder.createQuery(DM.class);
        Root<DM> root = query.from(DM.class);
        query.select(root);
        List<DM> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }

    @Override
    public List<TypeDM> getAllTypeDM() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TypeDM> query = builder.createQuery(TypeDM.class);
        Root<TypeDM> root = query.from(TypeDM.class);
        query.select(root);
        List<TypeDM> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }




    @Override
    public List<DMItem> getAllTypeDM_by_Service(String nomDepartement) {
        // Open a new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a query, <Lit> and Lit.class indicates that the query is of return type Lit
        CriteriaQuery<DMItem> criteria = builder.createQuery(DMItem.class);

        // This line is equivalent to writing "FROM t_lit" in the query, root contains columns of table t_lit
        Root<DMItem> root = criteria.from(DMItem.class);
        Join<DMItem, Espace> espaceJoin = root.join("espace");
        Join<Espace, Departement> departementJoin = espaceJoin.join("departement");

        // This line is equivalent to writing "Select * " in the query
        criteria.select(root)
                .where(builder.like(departementJoin.get("nomDepartement"), nomDepartement));

        // Execute the query and store the result into lits
        List<DMItem> lits = session.createQuery(criteria).getResultList();

        // Close session
       session.close();

        return lits;
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
    public TypeDM getTypeDmByName(String typenom,String categorie) {
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

        Predicate predicate1=builder.like(root.get("nomType"),typenom);

        Predicate predicate2=builder.like(root.get("categorie"),categorie);

        // Where nomDepartement = nomDepartement. Note that "nomDepartement" column is stored as Enum not String
        criteria.where(builder.and(predicate1,predicate2));

        // Execute query and store the result into departements
        TypeDM typeDM = session.createQuery(criteria).getSingleResult();

        session.close();
        return typeDM;
    }
   /* public static void main(String[] args) throws IOException {
        DMRepository DMR = new DMRepositoryImpl();
        List<DM> typeDMs = DMR.getAllDMByType("Mobilier");
        System.out.println(typeDMs);

       // DMR.saveTypeDM("mobilier");
    }
*/




    @Override
    public void affecter_dmItem(int id_dm, int id_espace) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DMItem dm = session.find(DMItem.class, id_dm);
        Espace espace=session.find(Espace.class,id_espace);
        dm.setEspace(espace);
        session.update(dm);
        session.getTransaction().commit();
        session.close();
    }





    @Override
    public DM getDMByName(String nom) {
        // Open new session
        Session session = sessionFactory.openSession();

        // Criteria Builder to build our queries
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create query of return type Departement
        CriteriaQuery<DM> criteria = builder.createQuery(DM.class);

        // Table or tables we want to select from
        Root<DM> root = criteria.from(DM.class);

        // Select *
        criteria.select(root);

        // Where nomDepartement = nomDepartement. Note that "nomDepartement" column is stored as Enum not String
        criteria.where(builder.equal(root.get("nom"),nom));

        // Execute query and store the result into departements
        DM dm = session.createQuery(criteria).getSingleResult();

        return dm;
    }




    // aya2

    @Override
    public void updateqteDM(String nom, int qte) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DM dm = getDMByName(nom);
        dm.setQuantite(qte);
        for (int i = 0; i < qte; i++) {
            int code = new Random().nextInt(1000);
            String codeNOM = dm.getNom() + String.format("%03d", code);
            DMItem dmItem = new DMItem(codeNOM, dm);
            session.save(dmItem);
            session.flush();
        }
        session.getTransaction().commit();
        session.close();
    }
    public List<DMItem> getAllDMItemByDM(String nomDM){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DMItem> criteria = builder.createQuery(DMItem.class);
        Root<DMItem> root = criteria.from(DMItem.class);
        Join<DMItem, DM> joinDM = root.join("DM");

        criteria.select(root);
        criteria.where(builder.equal(joinDM.get("nom"), nomDM));

        List<DMItem> dmItems = session.createQuery(criteria).getResultList();
        session.close();
        return dmItems;

    }

    @Override
    public void deleteDM(String nom) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DM dm = getDMByName(nom);
        if (dm.getQuantite()>0){
            List<DMItem> dmItems = getAllDMItemByDM(nom);
            for (DMItem dmItem : dmItems) {
                session.delete(dmItem);
            }
        }
        session.delete(dm);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void updateNomDM(String oldname, String newname) {
        List<DM> dmList = getAllDM();
        boolean exists = dmList.stream().anyMatch(dm -> dm.getNom().equals(newname));
        if (!exists) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            DM dm = getDMByName(oldname);
            dm.setNom(newname);
            session.update(dm);
            session.getTransaction().commit();
            session.close();
        }
    }

   /*public static void main(String[] args) throws IOException {
        DMRepository DMR = new DMRepositoryImpl();
       // List<DM> typeDMs = DMR.getAllDMByType("Mobilier");
       // System.out.println(typeDMs);
       // DMR.saveDM("table",2,DMR.getTypeDmByName("mobilier"));
       //System.out.println(DMR.getDMByName("Table"));
       //DMR.updateNomDM("Table","Nouvelle table");
       DMR.deleteDM("Chaise");
    }

    */















}
