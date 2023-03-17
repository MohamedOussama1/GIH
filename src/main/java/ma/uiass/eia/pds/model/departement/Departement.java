package ma.uiass.eia.pds.model.departement;

import ma.uiass.eia.pds.model.etage.Etage;
import ma.uiass.eia.pds.model.etage.Etage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_departement")
public  class Departement {
    @Id
    @Column(name = "departement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "departement_nom")
    String nomDepartement;
    int capacity;

    @ManyToOne
    @JoinColumn(name = "etage_id")
    Etage etage;
    public Departement(){}
    public Departement(String nomDepartement, int capacity, Etage etage) {
        this.nomDepartement = nomDepartement;
        this.capacity = capacity;
        this.etage = etage;
    }

    public Departement(String nomDepartement, Etage etage) {
        this.nomDepartement = nomDepartement;
        this.etage = etage;
    }

    public Departement(String nomDepartement, int capacity) {
        this.nomDepartement = nomDepartement;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Etage getEtage() {
        return etage;
    }

    public void setEtage(Etage etage) {
        this.etage = etage;
    }


}
