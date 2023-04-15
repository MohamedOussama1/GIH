package ma.uiass.eia.pds.model.Lit;

import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.reservation.Reservation;
import org.hibernate.validator.constraints.Range;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "t_lit_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LitItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lit_item_id")
    int id;
    @Column(name = "lit_item_code")
    String code;
    @Column(name = "etat_lit")
    @Enumerated(EnumType.STRING)
    EtatLit etat = EtatLit.NOUVEAU;
    @Column(name = "état_pourcentage_lit")
    @Range(min = 0, max = 100)
    BigDecimal percentEtat = BigDecimal.valueOf(100);
    @Column(name = "lit_occupé")
    Boolean occupied = false;
    @ManyToOne
    @JoinColumn(name = "lit_id")
    Lit litDescription;

    @ManyToOne
    @JoinColumn(name = "espace_id")
    Espace espace;
    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")
    Reservation reservation;
    public LitItem(){}

    public LitItem(int id, String code, EtatLit etat, BigDecimal percentEtat, Boolean occupied, Lit litDescription, Espace espace, Reservation reservation) {
        this.id = id;
        this.code = code;
        this.etat = etat;
        this.percentEtat = percentEtat;
        this.occupied = occupied;
        this.litDescription = litDescription;
        this.espace = espace;
        this.reservation = reservation;
    }

    public LitItem(String code, Lit litDescription, Espace espace) {
        this.code = code;
        this.litDescription = litDescription;
        this.espace = espace;
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

    public EtatLit getEtat() {
        return etat;
    }

    public void setEtat(EtatLit etat) {
        this.etat = etat;
    }

    public BigDecimal getPercentEtat() {
        return percentEtat;
    }

    public void setPercentEtat(BigDecimal percentEtat) {
        this.percentEtat = percentEtat;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }


    public Lit getLitDescription() {
        return litDescription;
    }

    public void setLitDescription(Lit litDescription) {
        this.litDescription = litDescription;
    }

    @JSONPropertyIgnore
    public Espace getEspace() {
        return espace;
    }

    public void setEspace(Espace espace) {
        this.espace = espace;
    }
    @JSONPropertyIgnore
    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "{" +
                "idLit:" + id +
                ", code:" + code  +
                ", occupied:" + occupied +
                ", litDescription:" + litDescription.getNumero() +
                ", typeEspace:" + espace.getClass() +
//                ", reservation:" + reservation +
//                ", espace:" + espace.getNomEspace() +
                ", idEspace:" + espace.getNumero() +
//                ", description:" + litDescription.getDescription() +
//                ", departement:" + espace.getDepartement() +
//                ", etage:" + espace.getDepartement().getEtage() +
//                ", batiment:" + espace.getDepartement().getEtage().getBatiment() +
                '}';
    }
}
