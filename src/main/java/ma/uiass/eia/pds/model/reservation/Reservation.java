package ma.uiass.eia.pds.model.reservation;

import ma.uiass.eia.pds.controller.LocalDateTimeAdapter;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.patient.Patient;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

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
    LocalDateTime dateFin;
    @Column(name = "date_fin_final")
    LocalDateTime dateFinFinal;
    @OneToOne
    @JoinColumn(name = "lit_item_id", referencedColumnName = "lit_item_id")
    LitItem lit;
    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    Patient patient;
    public Reservation(){}

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Reservation(LocalDateTime dateReservation, LocalDateTime dateDebut, LocalDateTime dateFin, LitItem lit, Patient patient) {
        this.dateReservation = dateReservation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lit = lit;
        this.patient = patient;
    }

    public LocalDateTime getDateDebutFinal() {
        return dateDebutFinal;
    }

    public void setDateDebutFinal(LocalDateTime dateDebutFinal) {
        this.dateDebutFinal = dateDebutFinal;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
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
        return "{" +
                "dateDebut:" + dateDebut +
                ", dateFin:" + dateFin +
                ", lit:" + lit.getCode() +
                '}';
    }
}
