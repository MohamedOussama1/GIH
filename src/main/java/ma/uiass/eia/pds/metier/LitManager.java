package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitEquipe;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.espace.Espace;

import java.util.List;

public interface LitManager {


    // Localisation
    void deplacerLit(String nomDepartement, String typeEspace, int numEspace, int idLit);

    //
    List<LitItem> getAllLit(String nomDepartement);
    List<LitItem> getAllDisponibleLit(String nomDepartement);
    List<LitItem> getAllOccupeLit(String nomDepartement);

    List<LitItem> getAllLitChambre(String nomDepartement);
    List<LitItem> getAllDisponibleLitChambre(String nomDepartement);
    List<LitItem> getAllOccupeLitChambre(String nomDepartement);
    double getUseRateChambre(String nomDepartement);

    List<LitItem> getAllLitSalle(String nomDepartement);
    List<LitItem> getAllDisponibleLitSalle(String nomDepartement);
    List<LitItem> getAllOccupeLitSalle(String nomDepartement);
    double getUseRateSalle(String nomDepartement);

}
