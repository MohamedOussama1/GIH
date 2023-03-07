package ma.uiass.eia.pds.model.espace.chambre;

import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.espace.Espace;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Chambre extends Espace {
    @Enumerated(value = EnumType.STRING)
    TypeChambre typeChambre;

    public Chambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }
    public Chambre(){}

    public Chambre(double superficie, Batiment batiment, TypeChambre typeChambre) {
        super(superficie, batiment);
        this.typeChambre = typeChambre;
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }
}
