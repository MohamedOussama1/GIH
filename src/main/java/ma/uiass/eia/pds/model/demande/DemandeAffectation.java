package ma.uiass.eia.pds.model.demande;


import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.departement.Departement;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "t_demande")
public class DemandeAffectation {
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

    @Enumerated(EnumType.STRING)
    @Column(name = "type_lit")
    private TypeLit typeLit;

    @Enumerated(EnumType.STRING)
    @Column(name = "model_lit")
    private ModelLit modelLit;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public DemandeAffectation() {
    }

    public DemandeAffectation(TypeLit typeLit, ModelLit modelLit, Departement departement, int qte) {
        this.id = id;
        this.departement=departement;
        this.etatDemande = EtatDemande.NONTRAITÉE;
        this.date_debut = LocalDate.now();
        this.typeLit = typeLit;
        this.modelLit = modelLit;
        this.qte = qte;
        this.date_fin= LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
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

    public TypeLit getTypeLit() {
        return typeLit;
    }

    public void setTypeLit(TypeLit typeLit) {
        this.typeLit = typeLit;
    }

    public ModelLit getModelLit() {
        return modelLit;
    }

    public void setModelLit(ModelLit modelLit) {
        this.modelLit = modelLit;
    }



    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "DemandeAffectation{" +
                "id=" + id +
                ", etatDemande=" + etatDemande +
                ", date_debut=" + date_debut +
                ", typeLit=" + typeLit +
                ", modelLit=" + modelLit +
                ", departement=" + departement +
                ", qte=" + qte +
                '}';
    }
}

