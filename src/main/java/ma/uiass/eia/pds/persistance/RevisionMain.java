package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Ambulance.Revision;

import java.util.List;

public class RevisionMain {
    public static void main(String[] args) {

   RevisionRepository revisionRepository = new RevisionRepositoryImpl();
   List<List<Revision>> lista = revisionRepository.get_m01_m02_m12_revision();
        System.out.println(lista.get(0).size());
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println(lista.get(1).size());
}
}
