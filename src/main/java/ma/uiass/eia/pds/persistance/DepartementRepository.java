package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.departement.Departement;

import java.util.List;

public interface DepartementRepository {
    void save(String nomDepartement);
    Departement findByName(String nomDepartement);
    List<Departement> findAllDepartement();
}
