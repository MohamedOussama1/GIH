package ma.uiass.eia.pds.model.Ambulance;


import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "t_revision")
public class Revision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revision")
    private int id;

    @Column(name = "date_revision")
    private LocalDate startDate;
    @Column(name = "date_sortie")
    private LocalDate endDate;
    @Column(name = "description_revision")
    private String description;

    @OneToOne
    @JoinColumn(name = "id_ambulance")
    private Ambulance ambulance;


    @Column(name = "etat_0")
    String etatAmbulance_0;

    @Column(name = "etat_1")
    String etatAmbulance_1;

    @Enumerated(EnumType.STRING)
    private TypeRevision typeRevision;

    public Revision() {
    }

    public Revision(LocalDate startDate, Ambulance ambulance, String etatAmbulance_0) {
        this.startDate = startDate;
        this.ambulance = ambulance;
        this.etatAmbulance_0 = etatAmbulance_0;
    }

    public Revision(LocalDate startDate, Ambulance ambulance, TypeRevision typeRevision, String etatAmbulance_0) {
        this.startDate = startDate;
        this.ambulance = ambulance;
        this.etatAmbulance_0 = etatAmbulance_0;
        this.typeRevision = typeRevision;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getEtatAmbulance_0() {
        return etatAmbulance_0;
    }

    public void setEtatAmbulance_0(String etatAmbulance_0) {
        this.etatAmbulance_0 = etatAmbulance_0;
    }

    public String getEtatAmbulance_1() {
        return etatAmbulance_1;
    }

    public void setEtatAmbulance_1(String etatAmbulance_1) {
        this.etatAmbulance_1 = etatAmbulance_1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Ambulance getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Ambulance ambulance) {
        this.ambulance = ambulance;
    }


    public TypeRevision getTypeRevision() {
        return typeRevision;
    }

    public void setTypeRevision(TypeRevision typeRevision) {
        this.typeRevision = typeRevision;
    }

    @Override
    public String toString() {
        return "Revision{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", ambulance=" + ambulance +
                ", etatAmbulance_0='" + etatAmbulance_0 + '\'' +
                ", etatAmbulance_1='" + etatAmbulance_1 + '\'' +
                ", typeRevision=" + typeRevision +
                '}';
    }
}







