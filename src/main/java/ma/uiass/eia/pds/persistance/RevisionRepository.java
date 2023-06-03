package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Ambulance.Ambulance;
import ma.uiass.eia.pds.model.Ambulance.Revision;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RevisionRepository {
    void updateAmbulanceRevision(String immatriculation);
    void createRevision(String immatriculation, LocalDate dateDebut, String description);
    List<String> getRevisionsByAmbulance(String immatriculation);
    void deleteRevision(int idRevision);
    List<String> getTypesRevision();




    ///  ================    rachid  =================
    public List<Revision> getAll_revision();

    public List<List<Revision>> get_m01_m02_m12_revision();

    public Revision get_last_revision_by_ambulance(Ambulance ambulance);

    void endCurrentRevision(String immatriculation, LocalDate endDate);

    public void updateRevision(int idRevision, String typeRevision, LocalDate dateDebut, LocalDate dateSortie, int ancienKm, int nouvelKm, String description);

    int isInRevision(String immatriculation);
}
