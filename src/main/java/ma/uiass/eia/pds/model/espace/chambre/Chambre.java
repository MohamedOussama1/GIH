package ma.uiass.eia.pds.model.espace.chambre;

import ma.uiass.eia.pds.model.departement.Departement;
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

    public Chambre(String nomEspace, int numero, double superficie, Departement departement, TypeChambre typeChambre) {
        super(nomEspace, numero, superficie, departement);
        this.typeChambre = typeChambre;
    }

    public Chambre(){}

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }
}
