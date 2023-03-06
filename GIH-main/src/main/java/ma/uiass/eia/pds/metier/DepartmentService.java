package ma.uiass.eia.pds.metier;


import ma.uiass.eia.pds.model.departement.Departement;

import java.util.List;


public interface DepartmentService {

    List<Departement> getDepartment();

    void addDepartment(Departement lit);

    void deleteDepartement(Long id);

    void updateDepartement(Long id);
}
