package ma.uiass.eia.pds.metier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RevisionService {
    void createRevision(String immatriculation, LocalDate dateDebut, String typeRevision);
    List<String> getRevisionsByAmbulance(String immatriculation);
    void updateRevision(int idRevision, String typeRevision, LocalDate dateDebut, LocalDate dateSortie, int ancienKm, int nouvelKm, String description);
    void deleteRevision(int idRevision);
    List<String> getTypesRevision();

    void endCurrentRevision(String immatriculation, LocalDate endDate);

    int isInRevision(String immatriculation);
}
