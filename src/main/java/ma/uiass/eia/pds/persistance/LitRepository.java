package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit;

import java.util.List;

public interface LitRepository {
    List<Lit> findAllLits();
    Lit findById(Long id);

    List<Lit> findLitByType(String title);

    void saveLit(Lit b);

    void deleteLit(Lit b);
}
