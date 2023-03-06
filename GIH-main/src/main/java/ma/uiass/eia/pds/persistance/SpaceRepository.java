package ma.uiass.eia.pds.persistance;




import ma.uiass.eia.pds.model.espace.Espace;

import java.util.List;

public interface SpaceRepository {


    List<Espace> findAllLits();
    Espace findById(Long id);

    List<Espace> findLitByType(String title);

    void saveLit(Espace b);

    void deleteSpce(Long b);


    void update(Long b);
}
