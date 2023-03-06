package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.TypeLit;
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
    public List<Lit> findByEtat(String etatLit) {
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

}
