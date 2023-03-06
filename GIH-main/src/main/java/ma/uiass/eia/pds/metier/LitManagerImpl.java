package ma.uiass.eia.pds.metier;


import ma.uiass.eia.pds.model.Lit.Lit;

import java.util.List;

public class LitManagerImpl implements LitManager {
    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public List<Lit> getAllLit(String nomDepartement) {
        return null;
    }

    @Override
    public List<Lit> getAllDisponibleLit(String nomDepartement) {
        return null;
    }

    @Override
    public List<Lit> getAllOccupeLit(String nomDepartement) {
        return null;
    }

    @Override
    public List<Lit> getAllLitChambre(String nomDepartement) {
        return null;
    }

    @Override
    public List<Lit> getAllDisponibleLitChambre(String nomDepartement) {
        return null;
    }

    @Override
    public List<Lit> getAllOccupeLitChambre(String nomDepartement) {
        return null;
    }

    @Override
    public double getUseRateChambre(String nomDepartement) {
        return 0;
    }

    @Override
    public List<Lit> getAllLitSalle(String nomDepartement) {
        return null;
    }

    @Override
    public List<Lit> getAllDisponibleLitSalle(String nomDepartement) {
        return null;
    }

    @Override
    public List<Lit> getAllOccupeLitSalle(String nomDepartement) {
        return null;
    }

    @Override
    public double getUseRateSalle(String nomDepartement) {
        return 0;
    }
}
