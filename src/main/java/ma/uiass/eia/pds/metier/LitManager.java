package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;

import java.util.List;
import java.util.Map;

public interface LitManager {


    // Localisation
    void deplacerLit(String nomDepartement, String typeEspace, int numEspace, int idLit);


    List<LitItem> getAllLit(String nomDepartement);
    List<LitItem> getAllDisponibleLit(String nomDepartement);
    List<LitItem> getAllOccupeLit(String nomDepartement);

    List<String> getAllLitChambre(String nomDepartement);
    List<String> getAllDisponibleLitChambre(String nomDepartement);
    List<String> getAllOccupeLitChambre(String nomDepartement);
    double getUseRateChambre(String nomDepartement);

    List<String> getAllLitSalle(String nomDepartement);
    List<String> getAllDisponibleLitSalle(String nomDepartement);
    List<String> getAllOccupeLitSalle(String nomDepartement);
    double getUseRateSalle(String nomDepartement);

}
