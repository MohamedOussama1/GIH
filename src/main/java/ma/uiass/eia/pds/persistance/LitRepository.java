package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.EtatLit;
import ma.uiass.eia.pds.model.Lit;
import ma.uiass.eia.pds.model.TypeLit;

import java.util.List;

public interface LitRepository {
    List<Lit> findAllLit();
    void saveLit(Lit lit);
    void updateLit(int id, EtatLit etatLit, TypeLit typeLit);
    void deleteLit(int id);
    List<Lit> findLitByEtat(String title);
}
