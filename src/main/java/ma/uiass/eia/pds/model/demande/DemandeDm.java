package ma.uiass.eia.pds.model.demande;


import ma.uiass.eia.pds.model.departement.Departement;
import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "t_demande_dm")
public class DemandeDm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demande_id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_demande")
    private EtatDemande etatDemande;

    @Column(name = "date_debut_demande")
    private LocalDate date_debut;

    @Column(name = "date_fin_demande")
    private LocalDate date_fin;
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public DemandeDm() {
    }

    public DemandeDm(EtatDemande etatDemande, LocalDate date_debut, Departement departement) {
        this.etatDemande = EtatDemande.INITIALISÉE;
        this.date_debut = LocalDate.now();
        this.departement = departement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EtatDemande getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(EtatDemande etatDemande) {
        this.etatDemande = etatDemande;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", etatDemande:" + etatDemande +
                ", dateDebut:" + date_debut +
                ", dateFin:" + date_fin +
                ", nomDepartement:" + departement.getNomDepartement() +
                '}';
    }
}
