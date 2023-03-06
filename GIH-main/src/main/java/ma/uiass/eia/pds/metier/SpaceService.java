package ma.uiass.eia.pds.metier;


import ma.uiass.eia.pds.model.espace.Espace;

import java.util.List;

public interface SpaceService {

    List<Espace> getSpace();

    void addSpace(Espace lit);

    void deleteSpace(Long id);

    void updateSpace(Long id);
}
