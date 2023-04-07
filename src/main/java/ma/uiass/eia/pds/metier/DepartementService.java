package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;

import java.util.List;

public interface DepartementService {
    void addDepartement(String nomDepartement);
    List<Departement> getAllDepartement();
    void addBatiment(Batiment batiment);
    List<Integer> getAllCodeEspace(String nomDepartement, String typeEspace);
    List<Integer> getAllCodeLit(String nomDepartement, int numEspace);
}
