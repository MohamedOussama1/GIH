package ma.uiass.eia.pds.model.fournisseur;

import javax.persistence.*;

@Entity(name = "t_fournisseur")
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fournisseur_id")
    int id;
    @Column(name = "fournisseur_nom")
    String nom;
    @Column(name = "fournisseur_ville")
    String ville;
    @Column(name = "fournisseur_email")
    String email;

    @Column(name = "fournisseur_codeape")
    String codeAPE;

    @Column(name = "fournisseur_formejuridique")
    String formeJuridique;

    @Column(name = "fournisseur_numsiren")
    String numSiren;

    @Column(name = "fournisseur_tel")
    String tel;

    public Fournisseur() {

    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    public String getEmail() {
        return email;
    }

    public String getCodeAPE() {
        return codeAPE;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public String getNumSiren() {
        return numSiren;
    }

    public String getTel() {
        return tel;
    }

    public Fournisseur(int id,String nom, String ville, String email, String codeAPE, String formeJuridique, String numSiren, String tel) {
        this.id=id;
        this.nom = nom;
        this.ville = ville;
        this.email = email;
        this.codeAPE = codeAPE;
        this.formeJuridique = formeJuridique;
        this.numSiren = numSiren;
        this.tel = tel;
    }
    public Fournisseur(String nom, String ville, String email, String codeAPE, String formeJuridique, String numSiren, String tel) {

        this.nom = nom;
        this.ville = ville;
        this.email = email;
        this.codeAPE = codeAPE;
        this.formeJuridique = formeJuridique;
        this.numSiren = numSiren;
        this.tel = tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCodeAPE(String codeAPE) {
        this.codeAPE = codeAPE;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public void setNumSiren(String numSiren) {
        this.numSiren = numSiren;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
