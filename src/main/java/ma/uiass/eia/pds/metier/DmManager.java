package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.DMItem;
import ma.uiass.eia.pds.model.dm.TypeDM;
import ma.uiass.eia.pds.model.espace.Espace;

import java.util.HashMap;
import java.util.List;

public interface DmManager {
    void saveDM(String nom, int quantité, TypeDM typeDM,String image_path);
    void saveTypeDM(String nom,String categorie);
    TypeDM getTypeDmByName(String typenom, String categorie);
    List<TypeDM> getAllTypeDM();
    List<DM> getAllDM();
    public List<DM> getAllDMByType(String typeDM);
    HashMap<Espace,List<DMItem>> get_items_Espace(String nomDepartement);

    void affecter_DmItem(int idDm, int idEscape);



    List<DMItem> getAllDMItemByDM(String nomDM);

    void deleteDM(String nom);
    void updateNomDM(String oldname, String newname);

}
