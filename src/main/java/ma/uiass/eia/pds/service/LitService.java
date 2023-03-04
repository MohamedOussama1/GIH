package ma.uiass.eia.pds.service;

import ma.uiass.eia.pds.model.EtatLit;
import ma.uiass.eia.pds.model.Lit;
import ma.uiass.eia.pds.model.TypeLit;

import java.util.List;

public interface LitService {
    List<Lit> getLits();
    List<Lit> getLitsByEtat(String etatLit);

    void addLit(Lit lit);
    void updateLit(int id, EtatLit etatLit, TypeLit typeLit);
    void deleteLit(int id);
    double calculateUseRate();

}
