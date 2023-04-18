package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.TypeDM;

import java.util.List;


public interface DMRepository {
    void saveDM(String nom, int quantité, TypeDM typeDM);
    void saveTypeDM(String nom);

    DM getAllDM();
    List<TypeDM> getAllTypeDM();

    List<DM> getAllDMByType(String typeDM);


    TypeDM getTypeDmByName(String typenom);
}
