package ma.uiass.eia.pds.model.demande;


import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.dm.TypeDM;

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
    @Column(name = "qte")
    private int qte;

    @ManyToOne
    @JoinColumn(name = "type_dm")
    private TypeDM typeDM;

    @Column(name = "nom_dm")
    private String nomDm;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public DemandeDm() {
    }

    public DemandeDm(int qte, TypeDM typeDM, String nomDm, Departement departement) {
        this.qte = qte;
        this.typeDM = typeDM;
        this.nomDm = nomDm;
        this.departement = departement;
        this.etatDemande = EtatDemande.NONTRAITÉE;
        this.date_debut = LocalDate.now();
        this.date_fin= LocalDate.now();
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

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public TypeDM getTypeDM() {
        return typeDM;
    }

    public void setTypeDM(TypeDM typeDM) {
        this.typeDM = typeDM;
    }

    public String getNomDm() {
        return nomDm;
    }

    public void setNomDm(String nomDm) {
        this.nomDm = nomDm;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
