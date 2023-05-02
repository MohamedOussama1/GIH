package ma.uiass.eia.pds.model.demande;

import ma.uiass.eia.pds.model.dm.DM;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;

@Entity(name = "t_detail_demande_dm")
public class DetailDemandeDm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    DM dm;
    @Column(name = "qte_dm")
    int qte;
    @Enumerated(EnumType.STRING)
    @Column(name = "etat_detail")
    private EtatDetail etatDetail;
    @ManyToOne
    @JoinColumn(name = "demande_id")
    DemandeDm demande;

    public DetailDemandeDm() {
    }

    public DetailDemandeDm(DM dm, int qte, DemandeDm demande) {
        this.dm = dm;
        this.qte = qte;
        this.demande = demande;
        this.etatDetail = EtatDetail.CONFIRMÉE;
    }

    public EtatDetail getEtatDetail() {
        return etatDetail;
    }

    public void setEtatDetail(EtatDetail etatDetail) {
        this.etatDetail = etatDetail;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @JSONPropertyIgnore
    public DemandeDm getDemande() {
        return demande;
    }

    public void setDemande(DemandeDm demande) {
        this.demande = demande;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public DM getDm() {
        return dm;
    }

    public void setDm(DM dm) {
        this.dm = dm;
    }
}
