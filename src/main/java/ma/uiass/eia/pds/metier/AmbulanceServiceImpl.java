package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Ambulance.Ambulance;
import ma.uiass.eia.pds.model.Ambulance.AmbulanceType;
import ma.uiass.eia.pds.persistance.AmbulanceRepository;
import ma.uiass.eia.pds.persistance.AmbulanceRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class AmbulanceServiceImpl implements AmbulanceService {

    AmbulanceRepository AS = new AmbulanceRepositoryImpl();

    @Override
    public Ambulance createAmbulance(String immatriculation, int km, LocalDate date_mise_en_service, AmbulanceType ambulanceType, String model) {
        return AS.createAmbulance(immatriculation,km,date_mise_en_service,ambulanceType,model);
    }
    @Override
    public Ambulance getAmbulanceById(int id) {
        return AS.getAmbulanceById(id);
    }

    @Override
    public List<Ambulance> getAllAmbulances() {
        return AS.getAllAmbulances();
    }

    @Override
    public void deleteAmbulance(int id) {
        AS.deleteAmbulance(id);
    }
//    @Override
//    public String getEtatAmbulanceById(int id) {
//        return AS.getEtatAmbulanceById(id);
//    }
//
//    @Override
//    public List<String> getAllEtatAmbulance() {
//        return AS.getAllEtatAmbulance();
//    }
//
//    @Override
//    public String getEtatAmbulanceByName(String nomEtat) {
//        return AS.getEtatAmbulanceByName(nomEtat);
//    }
//
    @Override
    public void updateAmbulance(int id, String immatriculation, LocalDate date_mise_service, int km, AmbulanceType ambulanceType, String model) {
        AS.updateAmbulance(id, immatriculation, date_mise_service, km, ambulanceType, model);
    }
}

