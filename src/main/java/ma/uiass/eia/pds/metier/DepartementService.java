package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.departement.Departement;

import java.util.List;

public interface DepartementService {
    void addDepartement(String nomDepartement);
    List<Departement> getAllDepartement();

    void addBatiment(Batiment batiment);
}
