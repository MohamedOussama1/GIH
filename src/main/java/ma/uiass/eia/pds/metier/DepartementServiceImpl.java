package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;
import ma.uiass.eia.pds.persistance.DepartementRepository;
import ma.uiass.eia.pds.persistance.DepartementRepositoryImpl;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<LitItem> getLitsStock(String nomDepartement) {
       return departementRepository.getAllLitStock(nomDepartement);
    }

    public void populateJsonListChambre(List<String> jsonList, Map<Chambre, List<LitItem>> espaceBedMap) {
        espaceBedMap.forEach((espace, litLst) -> {
            JSONObject espaceJson = new JSONObject();
            espaceJson.put("chambre", new JSONObject(espace));
            espaceJson.put("litLst", litLst);
            jsonList.add(espaceJson.toString());
        });
    }
}
