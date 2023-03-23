package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.persistance.DepartementRepository;
import ma.uiass.eia.pds.persistance.DepartementRepositoryImpl;

import java.util.List;

public class DepartementServiceImpl implements DepartementService {
    DepartementRepository departementRepository = new DepartementRepositoryImpl();
    @Override
    public void addDepartement(String nomDepartement) {
        departementRepository.save(nomDepartement);
    }

    @Override
    public List<Departement> getAllDepartement() {
        return departementRepository.findAllDepartement();
    }

    @Override
    public void addBatiment(Batiment batiment) {

    }

    @Override
    public List<Integer> getAllCodeEspace(String nomDepartement, String typeEspace) {
       return departementRepository.getAllCodeEspace(nomDepartement, typeEspace);
    }

    @Override
    public List<Integer> getAllCodeLit(String nomDepartement, int numEspace) {
        return departementRepository.getAllCodeLit(nomDepartement, numEspace);
    }
}
