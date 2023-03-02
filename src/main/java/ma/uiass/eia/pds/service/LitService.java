package ma.uiass.eia.pds.service;

import ma.uiass.eia.pds.model.Lit;

import java.util.List;

public interface LitService {
    List<Lit> getLits();

    void addLit(Lit lit);
}
