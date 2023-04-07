package ma.uiass.eia.pds.model.Lit;

import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity(name="t_lit")
public class Lit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lit_id")
    int numero;
    @Column(name = "type_lit")
    @Enumerated(EnumType.STRING)
    TypeLit type;
    @Enumerated(EnumType.STRING)
    @Column(name = "model_lit")
    ModelLit modelLit;
    @Column(name = "dimensions_lit")
    @Embedded
    Dimensions dimensions;
    @Column(name = "charge_max_lit")
    double chargeMax;
    @Column(name = "garantie_lit")
    Period garantie;
    @Column(name = "prix_lit")
    double prix;
    @Column(name = "front_color")
    String frontColor;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = FonctionLit.class)
    @CollectionTable(name = "t_fonction", joinColumns = @JoinColumn(name = "lit_id"))
    Set<FonctionLit> fonctionsLit = new HashSet<>();
    @Column(name = "description_lit")
    String description;
    public Lit(){}



    public Lit(TypeLit type, ModelLit modelLit, Dimensions dimensions, double chargeMax, Period garantie, double prix, String description) {
        this.type = type;
        this.modelLit = modelLit;
        this.dimensions = dimensions;
        this.chargeMax = chargeMax;
        this.garantie = garantie;
        this.prix = prix;
        this.description = description;
    }

    public Lit(TypeLit type, ModelLit modelLit, Dimensions dimensions, double chargeMax, Period garantie, double prix, Set<FonctionLit> fonctionsLit, String frontColor,  String description) {
        this.type = type;
        this.modelLit = modelLit;
        this.dimensions = dimensions;
        this.chargeMax = chargeMax;
        this.garantie = garantie;
        this.prix = prix;
        this.fonctionsLit = fonctionsLit;
        this.frontColor = frontColor;
        this.description = description;
    }

    @JSONPropertyIgnore
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
    @JSONPropertyIgnore
    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }
    @JSONPropertyIgnore
    public Period getGarantie() {
        return garantie;
    }

    public void setGarantie(Period garantie) {
        this.garantie = garantie;
    }

    @JSONPropertyIgnore
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


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TypeLit getType() {
        return type;
    }

    public void setType(TypeLit type) {
        this.type = type;
    }

    public String getFrontColor() {
        return frontColor;
    }

    public void setFrontColor(String frontColor) {
        this.frontColor = frontColor;
    }

    @Override
    public String toString() {
        return "{" +
                "numero:" + numero +
                ", type:" + type +
                ", modele:" + modelLit +
                ", dimensions:" + dimensions +
                ", chargeMax:" + chargeMax +
                ", prix:" + prix +
                ", garantie:" + garantie +
                ", description:" + description +
                "}";
    }
}
