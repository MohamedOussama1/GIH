package ma.uiass.eia.pds.model.dm;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitEquipe;

import javax.persistence.*;

@Entity(name="t_dm")
public class Dm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nom;
    @ManyToOne
    @JoinColumn(name = "lit_id")
    LitEquipe lit;
    public Dm(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LitEquipe getLit() {
        return lit;
    }

    public void setLit(LitEquipe lit) {
        this.lit = lit;
    }
}
