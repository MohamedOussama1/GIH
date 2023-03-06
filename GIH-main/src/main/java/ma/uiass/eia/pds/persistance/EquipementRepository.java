package ma.uiass.eia.pds.persistance;


import ma.uiass.eia.pds.model.dm.Dm;

import java.util.List;

public interface EquipementRepository {


    List<Dm> findAllLits();
    Dm findById(Long id);

    List<Dm> findLitByType(String title);

    void saveLit(Dm b);

    void deleteEquipement(Long b);

    void update(Long b);
}
