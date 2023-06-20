package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.persistance.RevisionRepository;
import ma.uiass.eia.pds.persistance.RevisionRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RevisionServiceImpl implements RevisionService{
    RevisionRepository revisionRepository = new RevisionRepositoryImpl();

    @Override
    public int createRevision(String immatriculation, LocalDate dateDebut, String typeRevision) {
        return revisionRepository.createRevision(immatriculation, dateDebut, typeRevision);
    }

    @Override
    public List<String> getRevisionsByAmbulance(String immatriculation) {
        return revisionRepository.getRevisionsByAmbulance(immatriculation);
    }

    @Override
    public void updateRevision(int idRevision, String typeRevision, LocalDate dateDebut, LocalDate dateSortie, int ancienKm, int nouvelKm, String description) {
        revisionRepository.updateRevision(idRevision, typeRevision, dateDebut, dateSortie, ancienKm, nouvelKm, description);
    }

    @Override
    public void deleteRevision(int idRevision) {
        revisionRepository.deleteRevision(idRevision);
    }

    @Override
    public List<String> getTypesRevision() {
        return revisionRepository.getTypesRevision();
    }

    @Override
    public void endCurrentRevision(String immatriculation, LocalDate endDate) {
        revisionRepository.endCurrentRevision(immatriculation, endDate);
    }

    @Override
    public int isInRevision(String immatriculation) {
        return revisionRepository.isInRevision(immatriculation);
    }

}
