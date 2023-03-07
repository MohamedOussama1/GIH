package ma.uiass.eia.pds.model.batiment;

import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;

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
    List<Espace> espaces = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "departement_id")
    Departement departement;

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

    public List<Espace> getEspaces() {
        return espaces;
    }

    public void setEspaces(List<Espace> espaces) {
        this.espaces = espaces;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
