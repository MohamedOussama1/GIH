package ma.uiass.eia.pds.model.Ambulance;


import ma.uiass.eia.pds.controller.LocalDateAdapter;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Entity(name = "t_revision")
public class Revision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revision")
    private int id;

    @Column(name = "date_revision")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @Column(name = "date_sortie")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
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
    @Column(name = "ancien_km")
    private int ancienKm;
    @Column(name = "nouvel_km")
    private int nouvelKm;

    public Revision() {
    }

    public Revision(LocalDate startDate, Ambulance ambulance, String etatAmbulance_0) {
        this.startDate = startDate;
        this.ambulance = ambulance;
        this.etatAmbulance_0 = etatAmbulance_0;
        this.ancienKm = ambulance.getKm();
    }

    public Revision(LocalDate startDate, Ambulance ambulance, TypeRevision typeRevision, String etatAmbulance_0) {
        this.startDate = startDate;
        this.ambulance = ambulance;
        this.etatAmbulance_0 = etatAmbulance_0;
        this.typeRevision = typeRevision;
        this.ancienKm = ambulance.getKm();
    }

    public int getAncienKm() {
        return ancienKm;
    }

    public void setAncienKm(int ancienKm) {
        this.ancienKm = ancienKm;
    }

    public int getNouvelKm() {
        return nouvelKm;
    }

    public void setNouvelKm(int nouvelKm) {
        this.nouvelKm = nouvelKm;
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
}
