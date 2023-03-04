package ma.uiass.eia.pds.service;

import ma.uiass.eia.pds.model.EtatLit;
import ma.uiass.eia.pds.model.Lit;
import ma.uiass.eia.pds.model.TypeLit;
import ma.uiass.eia.pds.persistance.LitRepository;
import ma.uiass.eia.pds.persistance.LitRepositoryImpl;

import java.util.List;

public class LitServiceImpl implements LitService{
    private LitRepository litRepository = new LitRepositoryImpl();
    @Override
    public void addLit(Lit lit) {
        litRepository.saveLit(lit);
    }
    @Override
    public List<Lit> getLits() {
        return litRepository.findAllLit();
    }

    @Override
    public List<Lit> getLitsByEtat(String etatLit) {
        return litRepository.findLitByEtat(etatLit);
    }

    @Override
    public void updateLit(int id, EtatLit etatLit, TypeLit typeLit) {
        litRepository.updateLit(id, etatLit, typeLit);
    }

    @Override
    public void deleteLit(int id) {
        litRepository.deleteLit(id);
    }

    @Override
    public double calculateUseRate() {
        // To implement
        return 0;
    }
}
