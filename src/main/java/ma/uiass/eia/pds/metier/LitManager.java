package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;

import java.util.List;
import java.util.Map;

public interface LitManager {


    // Localisation
    void deplacerLit(String nomDepartement, String typeEspace, int numEspace, int idLit);

    //
    List<LitItem> getAllLitStock();
    List<LitItem> getAllDisponibleLit(String nomDepartement);
    List<LitItem> getAllOccupeLit(String nomDepartement);

    Map<Chambre, List<LitItem>> getAllLitChambre(String nomDepartement);
    Map<Chambre, List<LitItem>> getAllDisponibleLitChambre(String nomDepartement);
    Map<Chambre, List<LitItem>> getAllOccupeLitChambre(String nomDepartement);
    double getUseRateChambre(String nomDepartement);

    Map<Salle, List<LitItem>> getAllLitSalle(String nomDepartement);
    Map<Salle, List<LitItem>> getAllDisponibleLitSalle(String nomDepartement);
    Map<Salle, List<LitItem>> getAllOccupeLitSalle(String nomDepartement);
    double getUseRateSalle(String nomDepartement);

}
