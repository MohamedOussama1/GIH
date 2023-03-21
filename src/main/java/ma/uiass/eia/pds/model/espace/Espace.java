package ma.uiass.eia.pds.model.espace;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitEquipe;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.departement.Departement;

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
    @Column(name = "espace_nom")
    String nomEspace;
    @Column(name = "espace_numero")
    int numero;
    double superficie;
    @ManyToOne
    @JoinColumn(name="departement_id")
    Departement departement;
    @OneToMany(mappedBy = "espace")
    List<LitEquipe> lstLitEquipe = new ArrayList<>();
    public Espace(){}

    public Espace(String nomEspace, int numero, double superficie, Departement departement) {
        this.nomEspace = nomEspace;
        this.numero = numero;
        this.superficie = superficie;
        this.departement = departement;
    }

    public Espace(String nomEspace, double superficie, Departement departement) {
        this.nomEspace = nomEspace;
        this.superficie = superficie;
        this.departement = departement;
    }

    public String getNomEspace() {
        return nomEspace;
    }

    public void setNomEspace(String nomEspace) {
        this.nomEspace = nomEspace;
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
