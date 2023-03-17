package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.espace.salle.Salle;

import java.util.List;

public interface SalleRepository {
    void saveSalle(String nomBatiment, double superficie, String nomSalle, String typeSalle);
    List<Lit> getAllLitSalle(String nomDepartement);
    public List<Lit> getAllOccupeLitSalle(String nomDepartement);
    public List<Lit> getAllDisponibleLitSalle(String nomDepartement);
}
