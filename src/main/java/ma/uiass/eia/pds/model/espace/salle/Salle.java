package ma.uiass.eia.pds.model.espace.salle;

import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.espace.Espace;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public  class Salle extends Espace {
    @Enumerated(value = EnumType.STRING)
    TypeSalle typeSalle;
    public Salle(){}
    public Salle(TypeSalle typeSalle) {
        this.typeSalle = typeSalle;
    }

    public Salle(double superficie, Batiment batiment, TypeSalle typeSalle) {
        super(superficie, batiment);
        this.typeSalle = typeSalle;
    }

    public TypeSalle getTypeSalle() {
        return typeSalle;
    }

    public void setTypeSalle(TypeSalle typeSalle) {
        this.typeSalle = typeSalle;
    }
}
