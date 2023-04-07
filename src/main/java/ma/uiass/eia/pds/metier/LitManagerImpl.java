package ma.uiass.eia.pds.metier;


import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;
import ma.uiass.eia.pds.persistance.*;

import java.util.List;
import java.util.Map;

public class LitManagerImpl implements LitManager {
    SalleRepository salleRepository = new SalleRepositoryImpl();
    ChambreRepository chambreRepository = new ChambreRepositoryImpl();
    LitRepository litRepository = new LitRepositoryImpl();

    @Override
    public void deplacerLit(String nomDepartement, String typeEspace, int numEspace, int idLit) {
        litRepository.deplacerLit(nomDepartement, typeEspace, numEspace, idLit);
    }

    @Override
    public List<LitItem> getAllLitStock() {
        return litRepository.findAllLitStock();
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
    public Map<Chambre, List<LitItem>> getAllLitChambre(String nomDepartement) {
        return chambreRepository.getAllLitChambre(nomDepartement);
    }

    @Override
    public Map<Chambre, List<LitItem>> getAllDisponibleLitChambre(String nomDepartement) {
        return chambreRepository.getAllDisponibleLitChambre(nomDepartement);
    }

    @Override
    public Map<Chambre, List<LitItem>> getAllOccupeLitChambre(String nomDepartement) {
        return chambreRepository.getAllOccupeLitChambre(nomDepartement);
    }

    @Override
    public double getUseRateChambre(String nomDepartement) {
        return 0;
    }

    @Override
    public  Map<Salle, List<LitItem>> getAllLitSalle(String nomDepartement) {
        return salleRepository.getAllLitSalle(nomDepartement);
    }

    @Override
    public Map<Salle, List<LitItem>> getAllDisponibleLitSalle(String nomDepartement) {
        return salleRepository.getAllDisponibleLitSalle(nomDepartement);
    }

    @Override
    public Map<Salle, List<LitItem>> getAllOccupeLitSalle(String nomDepartement) {
        return salleRepository.getAllOccupeLitSalle(nomDepartement);
    }

    @Override
    public double getUseRateSalle(String nomDepartement) {
        return 0;
    }
}
