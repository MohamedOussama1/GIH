package ma.uiass.eia.pds.model.patient;

import ma.uiass.eia.pds.model.reservation.Reservation;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "t_patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    int id;
    @Column(name = "patient_nom")
    String nom;
    @Column(name = "patient_prenom")
    String prenom;
    @Column(name = "date_de_naissance")
    LocalDate dateNaissance;
    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")
    Reservation reservation;
    double weight;
    double height;
    // A continuer (Maladies et

    public Patient(){}
    public Patient(String nom, String prenom, LocalDate dateNaissance, double weight, double height) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.weight = weight;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
