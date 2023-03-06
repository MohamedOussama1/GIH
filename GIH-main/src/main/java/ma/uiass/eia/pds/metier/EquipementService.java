package ma.uiass.eia.pds.metier;



import ma.uiass.eia.pds.model.dm.Dm;

import java.util.List;

public interface EquipementService {

    List<Dm> getLits();

    void addEuipement(Dm lit);

    void deleteEquipement(Long id);

    void updateEquipement(Long id);

}
