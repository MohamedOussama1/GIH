package ma.uiass.eia.pds.model.fournisseur;

import javax.persistence.*;

@Entity(name = "t_fournisseur")
public class Fournisseur {
    @Id
    @Column(name = "fournisseur_id")
    int id;
    @Enumerated(EnumType.STRING)
    NomFournisseur nom;
    @Column(name = "fournisseur_num_tel")
    String numTel;
    @Column(name = "fournisseur_email")
    String email;
}
