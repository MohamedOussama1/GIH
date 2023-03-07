package ma.uiass.eia.pds.model.espace;

import ma.uiass.eia.pds.model.Lit.LitEquipe;
import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.batiment.Batiment;

import javax.persistence.*;
import java.util.ArrayList;
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
    @ManyToOne
    @JoinColumn(name="batiment_id")
    Batiment batiment;
    @OneToMany(mappedBy = "espace")
    List<LitEquipe> lstLitEquipe = new ArrayList<>();
    public Espace(){}
    public Espace(double superficie, Batiment batiment) {
        this.superficie = superficie;
        this.batiment = batiment;
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

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }
}
