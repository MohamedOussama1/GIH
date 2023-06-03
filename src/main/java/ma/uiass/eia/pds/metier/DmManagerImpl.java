package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.DMItem;
import ma.uiass.eia.pds.model.dm.TypeDM;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.persistance.DMRepository;
import ma.uiass.eia.pds.persistance.DMRepositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DmManagerImpl implements DmManager {
    public DmManagerImpl(){};
    DMRepository dmRepository = new DMRepositoryImpl();

    DepartementService departementService=new DepartementServiceImpl();
    @Override
    public void saveDM(String nom, int quantité, TypeDM typeDM,String image_path) {
        dmRepository.saveDM(nom,quantité,typeDM,image_path);
    }

    @Override
    public void saveTypeDM(String nom, String categorie) {
        dmRepository.saveTypeDM(nom,categorie);

    }

    @Override
    public TypeDM getTypeDmByName(String typenom,String categorie) {

        return dmRepository.getTypeDmByName(typenom,categorie);
    }

    @Override
    public List<TypeDM> getAllTypeDM() {
        return dmRepository.getAllTypeDM();
    }

    @Override
    public List<DM> getAllDM() {
        return dmRepository.getAllDM();
    }

    @Override
    public List<DM> getAllDMByType(String typeDM) {
        return dmRepository.getAllDMByType(typeDM);
    }

    @Override
    public HashMap<Espace, List<DMItem>> get_items_Espace(String nomDepartement) {

        List<DMItem> lstallLit = dmRepository.getAllTypeDM_by_Service(nomDepartement);
        HashMap<Espace, List<DMItem>> mapchambre = new HashMap<>();



        for (DMItem item : lstallLit) {
            if (!mapchambre.containsKey(item.getEspace())) {
                mapchambre.put(item.getEspace(), new ArrayList<>());
            }
            mapchambre.get(item.getEspace()).add(item);
        }

//        departementService.getAllEspaceByService(nomDepartement)
//                .forEach(elt->{
//                    if(!mapchambre.containsKey(elt)) {
//                        mapchambre.put(elt, null);
//                    }
//                });

        return mapchambre;
    }

    @Override
    public void affecter_DmItem(int idDm, int idEscape) {
        dmRepository.affecter_dmItem(idDm,idEscape);}

   //aya2

    @Override
    public void deleteDM(String nom) {
        dmRepository.deleteDM(nom);
    }

    @Override
    public void updateNomDM(String oldname, String newname) {
        dmRepository.updateNomDM(oldname,newname);
    }

    @Override
    public List<DMItem> getAllDMItemByDM(String nomDM) {
        return dmRepository.getAllDMItemByDM(nomDM);
    }


}
