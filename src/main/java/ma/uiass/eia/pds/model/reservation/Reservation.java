package ma.uiass.eia.pds.model.reservation;

import ma.uiass.eia.pds.controller.LocalDateTimeAdapter;
import ma.uiass.eia.pds.model.Lit.Lit;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Entity(name = "t_reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    int id;
    @Column(name = "date_debut")
    LocalDateTime dateDebut;
    @Column(name = "date_fin")
    LocalDateTime dateFin;
    @OneToOne
    @JoinColumn(name = "lit_id", referencedColumnName = "lit_id")
    Lit lit;
    public Reservation(){}

    public Reservation(LocalDateTime dateDebut, LocalDateTime dateFin, Lit lit) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lit = lit;
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


    public Lit getLit() {
        return lit;
    }

    public void setLit(Lit lit) {
        this.lit = lit;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", lit=" + lit.getNumero() +
                '}';
    }
}
