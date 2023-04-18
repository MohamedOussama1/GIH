package ma.uiass.eia.pds.model.dm;

import javax.persistence.*;

@Entity(name="t_typedm")
public class TypeDM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typedm_id")
    private int id;
    @Column(name = "typedm_nom")
    private String nomType;

    public TypeDM (){};

    public TypeDM(String nomType){
        this.nomType=nomType;

    }

    @Override
    public String toString() {
        return  nomType ;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
}