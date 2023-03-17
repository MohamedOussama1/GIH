package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public interface LitRepository {
    List<Lit> findAllLit(String nomDepartement);
    void saveLit(TypeLit type, ModelLit modelLit, String dimensions, double chargeMax, Period garantie, double prix, String description);
    void saveManyLit(int quantity, int litDescriptionId);
    void updateLit(int id, EtatLit etatLit);
    void occuperLit(int id, LocalDateTime dateDebut, LocalDateTime dateFin);

    void deleteLit(int id);
    void deleteManyLits(List<Integer> idLitList);
}
