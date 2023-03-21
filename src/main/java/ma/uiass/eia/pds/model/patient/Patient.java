package ma.uiass.eia.pds.model.patient;

import javax.persistence.*;
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
    LocalDateTime dateNaissance;
    double weight;
    double height;
    // A continuer (Maladies etc)
}
