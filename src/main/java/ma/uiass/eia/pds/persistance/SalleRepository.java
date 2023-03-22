package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.espace.salle.Salle;

import java.util.List;

public interface SalleRepository {
    void saveSalle(String nomDepartement, int numero, double superficie, String nomSalle, String typeSalle);
    List<LitItem> getAllLitSalle(String nomDepartement);
    public List<LitItem> getAllOccupeLitSalle(String nomDepartement);
    public List<LitItem> getAllDisponibleLitSalle(String nomDepartement);
}
