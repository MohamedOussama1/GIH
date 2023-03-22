package ma.uiass.eia.pds.metier;


import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.persistance.*;

import java.util.List;

public class LitManagerImpl implements LitManager {
    SalleRepository salleRepository = new SalleRepositoryImpl();
    ChambreRepository chambreRepository = new ChambreRepositoryImpl();
    LitRepository litRepository = new LitRepositoryImpl();

    @Override
    public void deplacerLit(String nomDepartement, String typeEspace, int numEspace, int idLit) {
        litRepository.deplacerLit(nomDepartement, typeEspace, numEspace, idLit);
    }

    @Override
    public List<LitItem> getAllLit(String nomDepartement) {
        return litRepository.findAllLit(nomDepartement);
    }

    @Override
    public List<LitItem> getAllDisponibleLit(String nomDepartement) {
        return null;
    }

    @Override
    public List<LitItem> getAllOccupeLit(String nomDepartement) {
        return null;
    }

    @Override
    public List<LitItem> getAllLitChambre(String nomDepartement) {
        return chambreRepository.getAllLitChambre(nomDepartement);
    }

    @Override
    public List<LitItem> getAllDisponibleLitChambre(String nomDepartement) {
        return chambreRepository.getAllDisponibleLitChambre(nomDepartement);
    }

    @Override
    public List<LitItem> getAllOccupeLitChambre(String nomDepartement) {
        return chambreRepository.getAllOccupeLitChambre(nomDepartement);
    }

    @Override
    public double getUseRateChambre(String nomDepartement) {
        return 0;
    }

    @Override
    public List<LitItem> getAllLitSalle(String nomDepartement) {
        return salleRepository.getAllLitSalle(nomDepartement);
    }

    @Override
    public List<LitItem> getAllDisponibleLitSalle(String nomDepartement) {
        return salleRepository.getAllDisponibleLitSalle(nomDepartement);
    }

    @Override
    public List<LitItem> getAllOccupeLitSalle(String nomDepartement) {
        return salleRepository.getAllOccupeLitSalle(nomDepartement);
    }

    @Override
    public double getUseRateSalle(String nomDepartement) {
        return 0;
    }
}
