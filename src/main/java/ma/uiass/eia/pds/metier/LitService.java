package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.reservation.Reservation;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public interface LitService {
    List<Lit> getLits();
    List<Lit> findByEtat(String etatLit);

    void addLitDescription(TypeLit type, ModelLit modelLit, String dimensions, double chargeMax, Period garantie, double prix, String description);
    void addLits(int quantity, int litDescriptionId);
    void reserverLit(LocalDateTime dateDebut, LocalDateTime dateFin, int id);
    List<Reservation> findReservations(int idLit);
    void updateLit(int id, EtatLit etatLit);
    void deleteLit(int id);

}
