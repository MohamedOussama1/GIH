package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.Dimensions;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Set;

public interface LitRepository {
    List<String> findAllLitStock();
    List<LitItem> findAllLit(String nomDepartement);
    int saveLit(String type, String modelLit, String dimensions, double chargeMax, Period garantie, double prix, List<String> fonctionsLit, String frontColor, String description);
    void saveManyLit(int quantity, int litDescriptionId);
    void occuperLit(int idLit);

    void deleteLit(int id);

    void deplacerLit(String nomDepartement, String typeEspace, int numEspace, int idLit);
    List<String> getFonctionsLit();
    List<String> getTypesLit();
    List<String> getModelsLit();
}
