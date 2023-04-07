package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.salle.Salle;

import java.util.List;
import java.util.Map;

public interface SalleRepository {
    void saveSalle(String nomDepartement, int numero, double superficie, String nomSalle, String typeSalle);
    Map<Salle, List<LitItem>> getAllLitSalle(String nomDepartement);
    Map<Salle, List<LitItem>> getAllOccupeLitSalle(String nomDepartement);
    Map<Salle, List<LitItem>> getAllDisponibleLitSalle(String nomDepartement);
}
