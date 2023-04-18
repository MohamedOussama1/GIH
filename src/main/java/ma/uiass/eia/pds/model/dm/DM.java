package ma.uiass.eia.pds.model.dm;

import javax.persistence.*;

@Entity(name="t_dm")
public class DM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dm_id")
    int id;
    @Column(name = "dm_nom")
    String nom;
    @Column(name = "dm_qte")
    int quantite;

    @ManyToOne
    @JoinColumn(name = "type_dm")
    private TypeDM typeDM;



    public DM(){}

    public DM(String nom, int quantite, TypeDM typeDM) {
        this.nom = nom;
        this.quantite = quantite;
        this.typeDM=typeDM;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



}
