package ma.uiass.eia.pds.persistance;


import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;

import java.util.List;

public interface ChambreRepository {
    void saveChambre(String nomDepartement, int numero, double superficie, String nomChambre, String typeChambre);
    List<Chambre> getAllChambre();
    List<LitItem> getAllLitChambre(String nomDepartement);
    public List<LitItem> getAllOccupeLitChambre(String nomDepartement);
    public List<LitItem> getAllDisponibleLitChambre(String nomDepartement);
}
