package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.TypeLit;

import java.util.List;

public interface LitService {
    List<Lit> getLits();
    List<Lit> findByEtat(String etatLit);

    void addLit(Lit lit);
    void updateLit(int id, EtatLit etatLit, TypeLit typeLit);
    void deleteLit(int id);

}
