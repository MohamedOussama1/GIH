package ma.uiass.eia.pds.persistance;

public class DMMAIN {

    public static void main(String[] args) {

        DMRepositoryImpl dd=new DMRepositoryImpl();

        ChambreRepository chambre=new ChambreRepositoryImpl();

        SalleRepository salle=new SalleRepositoryImpl();

       //System.out.println(dd.getAllTypeDM_by_Service("Cardiologie"));

   //dd.affecter_dmItem(1,1);

        //System.out.println(chambre.getAllChambre("Cardiologie").size());
       // System.out.println(chambre.getAllChambre("Cardiologie"));

        System.out.println(salle.getAll_Salle("Cardiologie").size());
        System.out.println(salle.getAll_Salle("Cardiologie"));

    }
}
