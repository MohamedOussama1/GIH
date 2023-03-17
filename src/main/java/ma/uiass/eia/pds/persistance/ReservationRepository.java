package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.reservation.Reservation;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> getReservationsLit(int idLit);

}
