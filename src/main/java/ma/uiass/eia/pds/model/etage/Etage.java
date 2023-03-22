package ma.uiass.eia.pds.model.etage;

import ma.uiass.eia.pds.model.batiment.Batiment;

import javax.persistence.*;

@Entity(name = "t_etage")
public class Etage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etage_id")
    int id;
    @Column(name = "etage_reference")
    int reference;
    @ManyToOne
    @JoinColumn(name = "batiment_id")
    Batiment batiment;

    public Etage() {
    }

    public Etage(int reference, Batiment batiment) {
        this.reference = reference;
        this.batiment = batiment;
    }

    public Batiment getBatiment() {

        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    public Etage(int reference) {
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", reference:" + reference +
                ", batiment:" + batiment +
                '}';
    }
}
