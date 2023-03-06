package ma.uiass.eia.pds.metier;


import ma.uiass.eia.pds.model.dm.Dm;
import ma.uiass.eia.pds.persistance.EquipementRepository;
import ma.uiass.eia.pds.persistance.EquipementRepositoryImpl;

import java.util.List;

public class EquipementServiceImpl implements EquipementService{
    private EquipementRepository litRepository = new EquipementRepositoryImpl();

    @Override
    public List<Dm> getLits() {
        return  litRepository.findAllLits();
    }

    @Override
    public void addEuipement(Dm lit) {
        litRepository.saveLit(lit);

    }

    @Override
    public void deleteEquipement(Long id) {

    }

    @Override
    public void updateEquipement(Long id) {

    }







}
