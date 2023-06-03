package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.DMItem;
import ma.uiass.eia.pds.model.dm.TypeDM;

import java.util.List;


public interface DMRepository {
    void saveDM(String nom, int quantité, TypeDM typeDM,String image_path);

    void saveTypeDM(String nom, String categorie);

    List<DM> getAllDM();
    List<TypeDM> getAllTypeDM();

    List<DM> getAllDMByType(String typeDM);


    TypeDM getTypeDmByName(String typenom,String categorie);



    List<DMItem> getAllTypeDM_by_Service(String nomDepartement);

    void affecter_dmItem(int id_dm, int id_espace);

    DM getDMByName(String nom);

    //aya_update

    void updateqteDM(String nom, int qte);
    void deleteDM(String nom);
    void updateNomDM(String oldname, String newname);
    List<DMItem> getAllDMItemByDM(String nomDM);




}
