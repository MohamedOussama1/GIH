package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Ambulance.Ambulance;
import ma.uiass.eia.pds.model.Ambulance.AmbulanceType;

import java.time.LocalDate;
import java.util.List;

public interface AmbulanceService {
    Ambulance createAmbulance(String immatriculation, int km, LocalDate date_mise_en_service, AmbulanceType ambulanceType, String model);
    Ambulance getAmbulanceById(int id);
    List<Ambulance> getAllAmbulances();
    void deleteAmbulance(int id);
//    String getEtatAmbulanceById(int id);
//    List<String> getAllEtatAmbulance();
//    String getEtatAmbulanceByName(String nomEtat);
    void updateAmbulance(int id, String immatriculation, LocalDate date_mise_service, int km, AmbulanceType ambulanceType, String model) ;
}
