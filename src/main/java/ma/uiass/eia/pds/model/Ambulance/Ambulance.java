package ma.uiass.eia.pds.model.Ambulance;

import ma.uiass.eia.pds.controller.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Entity(name = "t_ambulance")
public class Ambulance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ambulance")
    private int id;
    @Column(name = "immatriculation", unique = true)
    private String immatriculation;

    @Column(name="date_mise_service")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date_mise_service;

    @Column(name = "etat_ambulance")
    private String etatAmbulance = "F";

    @Column(name = "estimated_date_revision")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate estimatedRevisionDate;
    @Column(name = "model")
    private String model;
    @Column(name="kilometrage")
    private int km;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_ambulance")
    private AmbulanceType ambulanceType;
    @OneToOne
    @JoinColumn(name = "id_revision")
    private Revision revision;

    public Ambulance(String immatriculation, LocalDate date_mise_service, int km,AmbulanceType ambulanceType, String model ) {
        this.model=model;
        this.immatriculation = immatriculation;
        this.ambulanceType=ambulanceType;
        this.date_mise_service = date_mise_service;
        this.etatAmbulance = "F";
        this.km = km;
    }
    public Ambulance() {
    }


    public EtatAmbulance getEtat_object(){
        EtatAmbulance etat=null;
        if(this.etatAmbulance.equals("F")){

            etat=new F();
        }

        if(this.etatAmbulance.equals("NFCD")){

            etat=new NFCD();
        }

        if(this.etatAmbulance.equals("NFLD")){

            etat=new NFLD();
        }

        return etat;
    }
    public Ambulance(String immatriculation, String etatAmbulances) {
        this.immatriculation = immatriculation;
        this.date_mise_service = LocalDate.now();
        this.etatAmbulance = etatAmbulances;
        this.km = 0;
    }

    public Ambulance(String immatriculation, int km) {
        this.immatriculation = immatriculation;
        this.km = km;
    }

    public Ambulance(String immatriculation, LocalDate date_mise_service, String etatAmbulance, LocalDate estimatedRevisionDate) {
        this.immatriculation = immatriculation;
        this.date_mise_service = date_mise_service;
        this.etatAmbulance = etatAmbulance;
        this.estimatedRevisionDate = estimatedRevisionDate;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    public LocalDate getDate_mise_service() {
        return date_mise_service;
    }

    public void setDate_mise_service(LocalDate date_mise_service) {
        this.date_mise_service = date_mise_service;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getEtatAmbulance() {
        return etatAmbulance;
    }

    public void setEtatAmbulance(String etatAmbulance) {
        this.etatAmbulance = etatAmbulance;
    }

    public LocalDate getEstimatedRevisionDate() {
        return estimatedRevisionDate;
    }

    public void setEstimatedRevisionDate(LocalDate estimatedRevisionDate) {
        this.estimatedRevisionDate = estimatedRevisionDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public AmbulanceType getAmbulanceType() {
        return ambulanceType;
    }

    public void setAmbulanceType(AmbulanceType ambulanceType) {
        this.ambulanceType = ambulanceType;
    }
}

