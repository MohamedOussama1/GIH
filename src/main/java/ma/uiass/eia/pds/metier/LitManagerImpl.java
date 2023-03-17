package ma.uiass.eia.pds.metier;


import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.persistance.*;

import java.util.List;

public class LitManagerImpl implements LitManager {
    SalleRepository salleRepository = new SalleRepositoryImpl();
    ChambreRepository chambreRepository = new ChambreRepositoryImpl();
    LitRepository litRepository = new LitRepositoryImpl();
    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public List<Lit> getAllLit(String nomDepartement) {
        return litRepository.findAllLit(nomDepartement);
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
        return chambreRepository.getAllLitChambre(nomDepartement);
    }

    @Override
    public List<Lit> getAllDisponibleLitChambre(String nomDepartement) {
        return chambreRepository.getAllDisponibleLitChambre(nomDepartement);
    }

    @Override
    public List<Lit> getAllOccupeLitChambre(String nomDepartement) {
        return chambreRepository.getAllOccupeLitChambre(nomDepartement);
    }

    @Override
    public double getUseRateChambre(String nomDepartement) {
        return 0;
    }

    @Override
    public List<Lit> getAllLitSalle(String nomDepartement) {
        return salleRepository.getAllLitSalle(nomDepartement);
    }

    @Override
    public List<Lit> getAllDisponibleLitSalle(String nomDepartement) {
        return salleRepository.getAllDisponibleLitSalle(nomDepartement);
    }

    @Override
    public List<Lit> getAllOccupeLitSalle(String nomDepartement) {
        return salleRepository.getAllOccupeLitSalle(nomDepartement);
    }

    @Override
    public double getUseRateSalle(String nomDepartement) {
        return 0;
    }
}
