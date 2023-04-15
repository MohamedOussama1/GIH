package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.Dimensions;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.reservation.Reservation;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface LitService {
    List<String> getFonctionsLit();
    List<String> getTypesLit();
    List<String> getModelsLit();
    List<String> getLitsStock();
    List<String> findByEtat(String etatLit);

    int addLitDescription(String type, String modelLit, String dimensions, double chargeMax, Period garantie, double prix, List<String> fonctions, String frontColor, String description);
    void addLits(int quantity, int litDescriptionId);
    void deleteLit(int id);

    // Reservations
    void reserverLit(int idLit);
    void annulerReservation(int idLit);
    Reservation findReservation(int idReservation);
    // DateVacation == null;
    List<Reservation> getCurrentReservations();
    List<Reservation> getCurrentReservation(Period period);
    List<Reservation> getReservationHistorique();
    List<Reservation> findReservations(int idLit);

    void occuperLit(int idlit);

    HashMap<Espace,List<LitItem>> getLitsEspace(String nomDepartement);
    List<LitItem> getLits(String nomDepartement);

    List<LitItem> Chercher_lit_disponbile_a_Reserver(String nomDepartment,String typeEspace);

    void saveExitDate(int idLit);
}
