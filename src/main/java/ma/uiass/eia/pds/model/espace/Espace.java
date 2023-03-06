package ma.uiass.eia.pds.model.espace;

import ma.uiass.eia.pds.model.Lit.LitEquipe;
import ma.uiass.eia.pds.model.departement.Departement;

import javax.persistence.*;
import java.util.List;

@Entity(name = "t_espace")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="espace_type",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class Espace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "espace_id")
    int id;

    double superficie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    Departement departement;
    @OneToMany(mappedBy = "espace")
    List<LitEquipe> lstLitEquipe;
    public Espace(){}
    public Espace(double superficie, Departement departement) {
        this.superficie = superficie;
        this.departement = departement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}