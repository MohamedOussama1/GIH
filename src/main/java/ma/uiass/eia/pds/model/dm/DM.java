package ma.uiass.eia.pds.model.dm;

import ma.uiass.eia.pds.model.livraison.DetailLivraison;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="t_dm")
public class DM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dm_id")
    int id;
    @Column(name = "dm_nom")
    String nom;
    @Column(name = "dm_qte")
    int quantite=0;

    @ManyToOne
    @JoinColumn(name = "type_dm")
    TypeDM typeDM;
    @Column(name = "image_path")
    String image_path;
    @OneToMany(mappedBy = "dm")
    List<DetailLivraison> detailsLivraison = new ArrayList<>();



    public DM(){}

    public DM(String nom, int quantite, TypeDM typeDM, String image_path) {
        this.nom = nom;
        this.quantite = quantite;
        this.typeDM = typeDM;
        this.image_path = image_path;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setTypeDM(TypeDM typeDM) {
        this.typeDM = typeDM;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    @JSONPropertyIgnore
    public List<DetailLivraison> getDetailsLivraison() {
        return detailsLivraison;
    }

    public void setDetailsLivraison(List<DetailLivraison> detailsLivraison) {
        this.detailsLivraison = detailsLivraison;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeDM getTypeDM() {
        return typeDM;
    }


    public JSONObject toJsonn(){
        JSONObject jo=new JSONObject();
        jo.put("id_dm",id);
        jo.put("nom",nom);
        jo.put("quantite",quantite);
        jo.put("typeDM",typeDM.toJsonn());
        jo.put("image_path",image_path);
        return jo;
    }
    @Override
    public String toString() {
        return "DM{" +
                "id=" + id +
                ", nom=" + nom + '\'' +
               ", quantite=" + quantite +
                ", typeDM=" + typeDM +
                '}';
    }
}
