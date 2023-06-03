package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.salle.Salle;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface SalleRepository {
    void saveSalle(String nomDepartement, int numero, double superficie, String nomSalle, String typeSalle);
    List<String> getAllLitSalle(String nomDepartement);
    List<String> getAllOccupeLitSalle(String nomDepartement);
    List<String> getAllDisponibleLitSalle(String nomDepartement);

    List<Salle> getAll_Salle(String cardiologie);
}
