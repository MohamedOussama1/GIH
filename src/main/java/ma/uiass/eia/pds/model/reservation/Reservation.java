package ma.uiass.eia.pds.model.reservation;

import ma.uiass.eia.pds.controller.LocalDateTimeAdapter;
import ma.uiass.eia.pds.model.Lit.LitItem;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "t_reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    int id;
    @Column(name = "date_reservation")
    LocalDateTime dateReservation;
    @Column(name = "date_debut")
    LocalDateTime dateDebut;
    @Column(name = "date_debut_final")
    LocalDateTime dateDebutFinal;
    @Column(name = "date_fin")
    LocalDateTime dateFinPredicted;
    @Column(name = "date_fin_final")
    LocalDateTime dateFinFinal;
    @OneToOne
    @JoinColumn(name = "lit_item_id", referencedColumnName = "lit_item_id")
    LitItem lit;

    public Reservation(){}

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Reservation(LocalDateTime dateReservation, LocalDateTime dateDebut, LocalDateTime dateFinPredicted, LitItem lit) {
        this.dateReservation = dateReservation;
        this.dateDebut = dateDebut;
        this.dateFinPredicted = dateFinPredicted;
        this.lit = lit;
    }

    public LocalDateTime getDateDebutFinal() {
        return dateDebutFinal;
    }

    public void setDateDebutFinal(LocalDateTime dateDebutFinal) {
        this.dateDebutFinal = dateDebutFinal;
    }


    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getDateFin() {
        return dateFinPredicted;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFinPredicted = dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LitItem getLit() {
        return lit;
    }

    public void setLit(LitItem lit) {
        this.lit = lit;
    }

    public LocalDateTime getDateFinFinal() {
        return dateFinFinal;
    }

    public void setDateFinFinal(LocalDateTime dateFinFinal) {
        this.dateFinFinal = dateFinFinal;
    }

    @Override
    public String toString() {
        DateTimeFormatter formater=DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        return "{" +
                "dateReservation :" +dateReservation.format(formater)
                +
                ", dateDebut  :  " + dateDebut.format(formater) +
                ", dateFin  :  " + dateFinPredicted.format(formater) +
                ", lit  :  " + lit.getCode() +
                "}";
    }
}