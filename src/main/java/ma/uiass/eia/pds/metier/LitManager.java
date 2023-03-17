package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitEquipe;

import java.util.List;

public interface LitManager {

    // Number of beds
    int getCapacity();

    //
    List<Lit> getAllLit(String nomDepartement);
    List<Lit> getAllDisponibleLit(String nomDepartement);
    List<Lit> getAllOccupeLit(String nomDepartement);

    List<Lit> getAllLitChambre(String nomDepartement);
    List<Lit> getAllDisponibleLitChambre(String nomDepartement);
    List<Lit> getAllOccupeLitChambre(String nomDepartement);
    double getUseRateChambre(String nomDepartement);

    List<Lit> getAllLitSalle(String nomDepartement);
    List<Lit> getAllDisponibleLitSalle(String nomDepartement);
    List<Lit> getAllOccupeLitSalle(String nomDepartement);
    double getUseRateSalle(String nomDepartement);

}
