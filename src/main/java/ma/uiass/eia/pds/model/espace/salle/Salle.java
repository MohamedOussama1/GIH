package ma.uiass.eia.pds.model.espace.salle;

import ma.uiass.eia.pds.model.departement.Departement;
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

    public Salle(String nomSalle, double superficie, Departement departement, TypeSalle typeSalle) {
        super(nomSalle, superficie, departement);
        this.typeSalle = typeSalle;
    }

    public TypeSalle getTypeSalle() {
        return typeSalle;
    }

    public void setTypeSalle(TypeSalle typeSalle) {
        this.typeSalle = typeSalle;
    }
}
