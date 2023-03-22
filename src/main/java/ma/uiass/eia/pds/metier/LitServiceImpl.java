package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.reservation.Reservation;
import ma.uiass.eia.pds.persistance.LitRepository;
import ma.uiass.eia.pds.persistance.LitRepositoryImpl;
import ma.uiass.eia.pds.persistance.ReservationRepository;
import ma.uiass.eia.pds.persistance.ReservationRepositoryImpl;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class LitServiceImpl implements LitService{
    private LitRepository litRepository = new LitRepositoryImpl();
    private ReservationRepository reservationRepository = new ReservationRepositoryImpl();
    @Override
    public void addLitDescription(TypeLit type, ModelLit modelLit, String dimensions, double chargeMax, Period garantie, double prix, String description) {
        litRepository.saveLit(type, modelLit, dimensions, chargeMax, garantie, prix, description);
    }

    @Override
    public void addLits(int quantity, int litDescriptionId) {
        litRepository.saveManyLit(quantity, litDescriptionId);
    }

    @Override
    public void reserverLit(LocalDateTime dateReservation, LocalDateTime dateDebut, LocalDateTime dateFin, int idLit, int idPatient) {
        litRepository.occuperLit(idLit, idPatient, dateReservation, dateDebut, dateFin);
    }

    @Override
    public void annulerReservation(int idLit) {

    }

    @Override
    public Reservation findReservation(int idReservation) {
        return null;
    }

    @Override
    public List<Reservation> getCurrentReservations() {
        return null;
    }

    @Override
    public List<Reservation> getCurrentReservation(Period period) {
        return null;
    }

    @Override
    public List<Reservation> getReservationHistorique() {
        return null;
    }

    @Override
    public List<Reservation> findReservations(int idLit) {
        return reservationRepository.getReservationsLit(idLit);
    }


    @Override
    public List<LitItem> getLits(String nomDepartement) {
        return litRepository.findAllLit(nomDepartement);
    }

    @Override
    public List<LitItem> findByEtat(String etatLit) {
        return null;
    }


    @Override
    public void deleteLit(int id) {
        litRepository.deleteLit(id);
    }

}
