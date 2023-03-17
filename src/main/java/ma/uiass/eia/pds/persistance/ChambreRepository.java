package ma.uiass.eia.pds.persistance;


import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;

import java.util.List;

public interface ChambreRepository {
    void saveChambre(String nomBatiment, double superficie, String nomChambre, String typeChambre);
    List<Chambre> getAllChambre();
    List<Lit> getAllLitChambre(String nomDepartement);
    public List<Lit> getAllOccupeLitChambre(String nomDepartement);
    public List<Lit> getAllDisponibleLitChambre(String nomDepartement);
}
