package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.reservation.Reservation;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> getReservationsLit(int idLit);


    List<Reservation> getAll();

    List<Reservation> getCurrentAll();


    Reservation findReservationById(int id_reservation);


    void saveReservation(int idlit);


    void Update_Les_Dates_Reel();

    void saveExitDate(int idLit);
}
