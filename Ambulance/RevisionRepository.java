package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Ambulance.Ambulance;
import ma.uiass.eia.pds.model.Ambulance.Revision;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RevisionRepository {
    void createRevision(String immatriculation, LocalDate dateDebut, String description);
    List<String> getRevisionsByAmbulance(String immatriculation);
    void updateRevision(int idRevision, LocalDate dateDevut, String description);
    void deleteRevision(int idRevision);
    List<String> getTypesRevision();




    ///  ================    rachid  =================
    public List<Revision> getAll_revision();

    public List<List<Revision>> get_m01_m02_m12_revision();

    public Revision get_last_revision_by_ambulance(Ambulance ambulance);
}
