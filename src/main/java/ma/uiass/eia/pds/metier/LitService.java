package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.Dimensions;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.reservation.Reservation;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Set;

public interface LitService {
    List<LitItem> getLitsStock();
    List<LitItem> findByEtat(String etatLit);

    int addLitDescription(TypeLit type, ModelLit modelLit, Dimensions dimensions, double chargeMax, Period garantie, double prix, Set<FonctionLit> fonctionsLit, String frontColor, String description);
    void addLits(int quantity, int litDescriptionId);
    void deleteLit(int id);

    // Reservations
    void reserverLit(LocalDateTime dateReservation, LocalDateTime dateDebut, LocalDateTime dateFin, int idLit, int idPatient);
    void annulerReservation(int idLit);
    Reservation findReservation(int idReservation);
    // DateVacation == null;
    List<Reservation> getCurrentReservations();
    List<Reservation> getCurrentReservation(Period period);
    List<Reservation> getReservationHistorique();
    List<Reservation> findReservations(int idLit);


}
