package ma.uiass.eia.pds.model.Lit;

import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.reservation.Reservation;

import javax.persistence.*;

@Entity(name = "t_lit_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LitItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lit_item_id")
    int id;
    @Column(name = "lit_item_code")
    String code;
    @ManyToOne
    @JoinColumn(name = "description_lit_id", referencedColumnName = "lit_id")
    Lit litDescription;

    @ManyToOne
    @JoinColumn(name = "espace_id")
    Espace espace;
    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")
    Reservation reservation;
    public LitItem(){}

    public LitItem(String code, Lit litDescription, Espace espace, Reservation reservation) {
        this.code = code;
        this.litDescription = litDescription;
        this.espace = espace;
        this.reservation = reservation;
    }

    public LitItem(String code, Lit litDescription) {
        this.code = code;
        this.litDescription = litDescription;
    }
    public LitItem(Lit litDescription){
        this.litDescription = litDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Lit getLitDescription() {
        return litDescription;
    }

    public void setLitDescription(Lit litDescription) {
        this.litDescription = litDescription;
    }

    public Espace getEspace() {
        return espace;
    }

    public void setEspace(Espace espace) {
        this.espace = espace;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", code:" + code  +
                ", litDescription" + litDescription +
                ", espace" + espace +
                ", reservation:" + reservation +
                ", espace:" + espace.getNomEspace() +
                ", numEspace:" + espace.getId() +
                ", departement:" + espace.getDepartement() +
                ", etage:" + espace.getDepartement().getEtage() +
                ", batiment:" + espace.getDepartement().getEtage().getBatiment() +
                '}';
    }
}
