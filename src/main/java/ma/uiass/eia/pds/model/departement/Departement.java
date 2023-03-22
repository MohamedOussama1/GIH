package ma.uiass.eia.pds.model.departement;

import ma.uiass.eia.pds.model.etage.Etage;

import javax.persistence.*;

@Entity
@Table(name="t_departement")
public  class Departement {
    @Id
    @Column(name = "departement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "departement_nom")
    String nomDepartement;
    int capacityMax;

    @ManyToOne
    @JoinColumn(name = "etage_id")
    Etage etage;
    public Departement(){}
    public Departement(String nomDepartement, int capacityMax, Etage etage) {
        this.nomDepartement = nomDepartement;
        this.capacityMax = capacityMax;
        this.etage = etage;
    }


    public Departement(String nomDepartement, int capacityMax) {
        this.nomDepartement = nomDepartement;
        this.capacityMax = capacityMax;
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

    public int getCapacityMax() {
        return capacityMax;
    }

    public void setCapacityMax(int capacityMax) {
        this.capacityMax = capacityMax;
    }

    public Etage getEtage() {
        return etage;
    }

    public void setEtage(Etage etage) {
        this.etage = etage;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", nomDepartement:" + nomDepartement +
                ", capacityMax:" + capacityMax +
//                ", etage:" + etage +
                '}';
    }
}
