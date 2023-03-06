package ma.uiass.eia.pds.persistance;



import ma.uiass.eia.pds.model.departement.Departement;

import java.util.List;

public interface DepartmentRpository {


    List<Departement> findAllLits();


    Departement findById(Long id);

    List<Departement> findLitByType(String title);

    void saveLit(Departement b);

    void deleteDepatement(Long b);

    void update(Long b);
}
