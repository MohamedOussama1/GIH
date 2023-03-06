package ma.uiass.eia.pds.metier;


import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.persistance.SpaceRepository;
import ma.uiass.eia.pds.persistance.SpaceRepositoryImpl;

import java.util.List;

public class SpaceServiceImpl implements SpaceService{
    private SpaceRepository litRepository = new SpaceRepositoryImpl();
    @Override
    public List<Espace> getSpace() {
        return  litRepository.findAllLits();
    }

    @Override
    public void addSpace(Espace lit) {
        litRepository.saveLit(lit);
    }

    @Override
    public void deleteSpace(Long id) {

    }

    @Override
    public void updateSpace(Long id) {

    }


}
