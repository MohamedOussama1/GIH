package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;

import java.util.List;
import java.util.Map;

public interface DepartementService {
    void addDepartement(String nomDepartement);
    List<Departement> getAllDepartement();
    void addBatiment(Batiment batiment);
    List<Integer> getAllCodeEspace(String nomDepartement, String typeEspace);
    List<Integer> getAllCodeLit(String nomDepartement, int numEspace);

    List<LitItem> getLitsStock(String nomDepartement);
}
