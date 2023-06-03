package ma.uiass.eia.pds.model.livraison;

import ma.uiass.eia.pds.model.dm.DM;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;

@Entity(name = "t_detaillivraison")
public class DetailLivraison {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "detaillivraison_id")
    int id;
    @Column(name = "detaillivraison_qte")
    int qte;

    @ManyToOne
    @JoinColumn(name = "dm_id")
    DM dm;

    @ManyToOne
    @JoinColumn(name = "livraison_id")
    LivraisonDM livraisonDM;

    public DetailLivraison() {

    }

    public DetailLivraison(int qte, DM dm, LivraisonDM livraisonDM) {
        this.qte = qte;
        this.dm = dm;
        this.livraisonDM = livraisonDM;
    }

    public int getId() {
        return id;
    }

    public int getQte() {
        return qte;
    }

    public DM getDm() {
        return dm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setDm(DM dm) {
        this.dm = dm;
    }

    @JSONPropertyIgnore
    public LivraisonDM getLivraisonDM() {
        return livraisonDM;
    }

    public void setLivraisonDM(LivraisonDM livraisonDM) {
        this.livraisonDM = livraisonDM;
    }

    public DetailLivraison(int qte, DM dm) {
        this.qte = qte;
        this.dm = dm;

    }
}
