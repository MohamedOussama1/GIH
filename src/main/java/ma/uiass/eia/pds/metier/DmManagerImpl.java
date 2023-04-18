package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.TypeDM;
import ma.uiass.eia.pds.persistance.DMRepository;
import ma.uiass.eia.pds.persistance.DMRepositoryImpl;

import java.util.List;

public class DmManagerImpl implements DmManager {
    public DmManagerImpl(){};
    DMRepository dmRepository = new DMRepositoryImpl();
    @Override
    public void saveDM(String nom, int quantité, TypeDM typeDM) {
        dmRepository.saveDM(nom,quantité,typeDM);
    }

    @Override
    public void saveTypeDM(String nom) {
        dmRepository.saveTypeDM(nom);

    }

    @Override
    public TypeDM getTypeDmByName(String typenom) {
        return dmRepository.getTypeDmByName(typenom);
    }

    @Override
    public List<TypeDM> getAllTypeDM() {
        return dmRepository.getAllTypeDM();
    }

    @Override
    public List<DM> getAllDMByType(String typeDM) {
        return dmRepository.getAllDMByType(typeDM);
    }

}
