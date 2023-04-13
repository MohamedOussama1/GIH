package ma.uiass.eia.pds.model.dm;

import ma.uiass.eia.pds.model.Lit.LitItem;

import javax.persistence.*;

@Entity(name="t_dm")
public class Dm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nom;
    @ManyToOne
    @JoinColumn(name = "lit_id")
    LitItem lit;
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

    public LitItem getLit() {
        return lit;
    }

    public void setLit(LitItem lit) {
        this.lit = lit;
    }
}
