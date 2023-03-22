package ma.uiass.eia.pds.model.batiment;

import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.etage.Etage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "t_batiment")
public class Batiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batiment_id")
    int id;
    @Column(name = "batiment_nom")
    String nomBatiment;
    @OneToMany(mappedBy = "batiment")
    List<Etage> etage = new ArrayList<>();
    public Batiment(){}
    public Batiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomBatiment() {
        return nomBatiment;
    }

    public void setNomBatiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }

    public List<Etage> getEtage() {
        return etage;
    }

    public void setEtage(List<Etage> etage) {
        this.etage = etage;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", nomBatiment:" + nomBatiment +
                ", etage=" + etage +
                '}';
    }
}
