package ma.uiass.eia.pds.service;

import ma.uiass.eia.pds.model.Lit;
import ma.uiass.eia.pds.persistance.LitRepository;
import ma.uiass.eia.pds.persistance.LitRepositoryImpl;

import java.util.List;

public class LitServiceImpl implements LitService{
    private LitRepository litRepository = new LitRepositoryImpl();
    @Override
    public List<Lit> getLits() {
        return  litRepository.findAllLits();
    }

    @Override
    public void addLit(Lit lit) {
        litRepository.saveLit(lit);
    }
}
