package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Ambulance.Ambulance;
import ma.uiass.eia.pds.model.Ambulance.AmbulanceType;

import java.time.LocalDate;
import java.util.List;

public interface AmbulanceRepository {
     Ambulance getAmbulanceByImmatriculation(String immatriculation);

     Ambulance createAmbulance(String immatriculation, int km, LocalDate date_mise_en_service, AmbulanceType ambulanceType, String model) ;
     void updateAmbulance(int id, String immatriculation, LocalDate date_mise_service, int km,  AmbulanceType ambulanceType, String model) ;
     Ambulance getAmbulanceById(int id);
     List<Ambulance> getAllAmbulances();
     void deleteAmbulance(int id);

//     EtatAmbulance createEtatAmbulance(String nom);

//     String getEtatAmbulanceById(int id);
//
//     String getEtatAmbulanceByName(String nomEtat);
//
//     List<String> getAllEtatAmbulance();

}
