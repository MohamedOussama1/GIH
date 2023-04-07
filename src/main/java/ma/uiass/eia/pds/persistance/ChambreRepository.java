package ma.uiass.eia.pds.persistance;


import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;

import java.util.List;
import java.util.Map;

public interface ChambreRepository {
    void saveChambre(String nomDepartement, int numero, double superficie, String nomChambre, String typeChambre);
    List<Chambre> getAllChambre();
    Map<Chambre, List<LitItem>> getAllLitChambre(String nomDepartement);
    Map<Chambre, List<LitItem>> getAllOccupeLitChambre(String nomDepartement);
    Map<Chambre, List<LitItem>> getAllDisponibleLitChambre(String nomDepartement);
}
