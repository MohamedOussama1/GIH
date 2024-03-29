package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.departement.Departement;

import java.util.List;

public interface DepartementRepository {
    void save(String nomDepartement);
    Departement findByName(String nomDepartement);
    List<Departement> findAllDepartement();

    List<Integer> getAllCodeEspace(String nomDepartement, String typeEspace);

    List<Integer> getAllCodeLit(String nomDepartement, int numEspace);

    List<LitItem> getAllLitStock(String nomDepartement);
}
