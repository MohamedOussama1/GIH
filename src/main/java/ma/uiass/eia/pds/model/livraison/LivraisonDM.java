package ma.uiass.eia.pds.model.livraison;

import ma.uiass.eia.pds.model.fournisseur.Fournisseur;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "t_livraisondm")
public class LivraisonDM {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "livraison_id")
    int id;
    @Column(name = "date_livraison")
    LocalDateTime dateLivraison;

    @ManyToOne
    @JoinColumn(name="fournisseur_livraison")
    Fournisseur fournisseur;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "livraisonDM")
    List<DetailLivraison> detailLivraisons = new ArrayList<>();


    public LivraisonDM( Fournisseur fournisseur, List<DetailLivraison> detailLivraisons) {
        this.id = id;
        this.dateLivraison = LocalDateTime.now();
        this.fournisseur = fournisseur;
        this.detailLivraisons=detailLivraisons;


    }

    public LivraisonDM() {

    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }



    public void setId(int id) {
        this.id = id;
    }

    public List<DetailLivraison> getDetailLivraisons() {
        return detailLivraisons;
    }

    public void setDetailLivraisons(List<DetailLivraison> detailLivraisons) {
        this.detailLivraisons = detailLivraisons;
    }

    public void setDateLivraison(LocalDateTime dateLivraison) {
        this.dateLivraison= dateLivraison;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }






}
