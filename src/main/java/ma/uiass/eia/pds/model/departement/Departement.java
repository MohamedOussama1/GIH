package ma.uiass.eia.pds.model.departement;

import ma.uiass.eia.pds.model.batiment.Batiment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_departement")
public  class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int department_id;
    @Enumerated(EnumType.STRING)
    NomDepartement nomDepartement;
    int capacity;
    @OneToMany(mappedBy = "departement")
    List<Batiment> batiment = new ArrayList<>();
    public Departement(){}
    public Departement(NomDepartement nomDepartement, int capacity, List<Batiment> batiment) {
        this.nomDepartement = nomDepartement;
        this.capacity = capacity;
        this.batiment = batiment;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id= department_id;
    }

    public NomDepartement getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(NomDepartement nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Batiment> getbatiments() {
        return batiment;
    }

    public void setbatiments(List<Batiment> batiment) {
        this.batiment = batiment;
    }


}
