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
    List<LitItem> findAllLitStock();
    int saveLit(TypeLit type, ModelLit modelLit, Dimensions dimensions, double chargeMax, Period garantie, double prix, Set<FonctionLit> fonctionsLit, String frontColor, String description);
    void saveManyLit(int quantity, int litDescriptionId);
    void occuperLit(int idLit, int idPatient, LocalDateTime dateReservation, LocalDateTime dateDebut, LocalDateTime dateFin);

    void deleteLit(int id);

    void deplacerLit(String nomDepartement, String typeEspace, int numEspace, int idLit);
}
