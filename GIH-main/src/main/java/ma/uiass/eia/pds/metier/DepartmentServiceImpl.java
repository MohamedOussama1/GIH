package ma.uiass.eia.pds.metier;


import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.persistance.DepartmentRepositoryImpl;
import ma.uiass.eia.pds.persistance.DepartmentRpository;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService{
    private DepartmentRpository litRepository = new DepartmentRepositoryImpl();
    @Override
    public List<Departement> getDepartment() {
        return  litRepository.findAllLits();
    }

    @Override
    public void addDepartment(Departement lit) {
        litRepository.saveLit(lit);
    }

    @Override
    public void deleteDepartement(Long id) {

    }

    @Override
    public void updateDepartement(Long id) {

    }


}
