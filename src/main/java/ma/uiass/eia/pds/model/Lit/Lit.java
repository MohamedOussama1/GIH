package ma.uiass.eia.pds.model.Lit;

import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.reservation.Reservation;

import javax.persistence.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name="t_lit")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Lit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lit_id")
    int numero;
    @Column(name = "etat_lit")
    @Enumerated(EnumType.STRING)
    EtatLit etat = EtatLit.DISPONIBLE;
    @Column(name = "type_lit")
    @Enumerated(EnumType.STRING)
    TypeLit type;
    @Enumerated(EnumType.STRING)
    @Column(name = "model_lit")
    ModelLit modelLit;
    @Column(name = "dimensions_lit")
    String dimensions;
    @Column(name = "charge_max_lit")
    double chargeMax;
    @Column(name = "garantie_lit")
    Period garantie;
    @Column(name = "prix_lit")
    double prix;
    @Enumerated
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "t_lit_fonction",
            joinColumns = {@JoinColumn(name = "lit_id")},
            inverseJoinColumns = {@JoinColumn(name = "fonction_id")}
    )
    Set<FonctionLit> fonctionsLit = new HashSet<>();
    @Column(name = "description_lit")
    String description;
    @OneToMany(mappedBy = "litDescription")
    List<LitItem> litsItem = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "espace_id")
    Espace espace;
    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")
    Reservation reservation;
    public Lit(){}

    public Lit(TypeLit type, ModelLit modelLit, String dimensions, double chargeMax, Period garantie, double prix, String description) {
        this.type = type;
        this.modelLit = modelLit;
        this.dimensions = dimensions;
        this.chargeMax = chargeMax;
        this.garantie = garantie;
        this.prix = prix;
        this.description = description;
    }
    public Set<FonctionLit> getFonctionsLit() {
        return fonctionsLit;
    }

    public void setFonctionsLit(Set<FonctionLit> fonctionsLit) {
        this.fonctionsLit = fonctionsLit;
    }

    public ModelLit getModelLit() {
        return modelLit;
    }

    public double getChargeMax() {
        return chargeMax;
    }

    public void setChargeMax(double chargeMax) {
        this.chargeMax = chargeMax;
    }

    public void setModelLit(ModelLit modelLit) {
        this.modelLit = modelLit;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Period getGarantie() {
        return garantie;
    }

    public void setGarantie(Period garantie) {
        this.garantie = garantie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LitItem> getLitItemList() {
        return litsItem;
    }

    public void setLitItemList(List<LitItem> litsItem) {
        this.litsItem = litsItem;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


    public Espace getEspace() {
        return espace;
    }

    public void setEspace(Espace espace) {
        this.espace = espace;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EtatLit getEtat() {
        return etat;
    }

    public void setEtat(EtatLit etat) {
        this.etat = etat;
    }

    public TypeLit getType() {
        return type;
    }

    public void setType(TypeLit type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "numero:" + numero +
                ", etat:" + etat +
                ", type:" + type +
                ", modele:" + modelLit +
                ", dimensions:" + dimensions +
                ", chargeMax:" + chargeMax +
                ", prix:" + prix +
                ", garantie:" + garantie +
                ", description:" + description +
                ", espace:" + espace.getNomEspace() +
                ", numEspace:" + espace.getId() +
                ", departement:" + espace.getDepartement() +
                ", etage:" + espace.getDepartement().getEtage() +
                ", batiment:" + espace.getDepartement().getEtage().getBatiment() +
                '}';
    }
}
