package ma.uiass.eia.pds;

import ma.uiass.eia.pds.persistance.FournisseurRepository;
import ma.uiass.eia.pds.persistance.FournisseurRepositoryImpl;

public class Main2 {

    public static void main(String[] args) {

        FournisseurRepository dd=new FournisseurRepositoryImpl();

        dd.saveFournisseur("rahcid","rachid","rachid","rachid","rahcid","rachid","rachid");


    }
}
