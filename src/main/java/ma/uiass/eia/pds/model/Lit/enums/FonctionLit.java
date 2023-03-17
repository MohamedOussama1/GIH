package ma.uiass.eia.pds.model.Lit.enums;


import ma.uiass.eia.pds.model.Lit.Lit;

import javax.persistence.*;
import java.util.List;

@Entity(name = "t_fonction")
public enum FonctionLit {
    REGLAGE_DE_LA_HAUTEUR(0, "Réglage de la hauteur"),
    REGLAGE_DOSSIER(1, "Réglage de la position du dossier"),
    POSITION_JAMBES(2, "Réglage de la position des jambes"),
    INCLINAISON_LIT(3, "Réglage de l'inclinaison du lit"),
    TRENDELENBURG(4, "Réglage de la fonction Trendelenburg"),
    ANTI_TRENDELENBURG(5, "Réglage de la fonction anti-Trendelenburg"),
    BARRIERES_SECURITE(6,"Réglage des barrières de la sécurité"),
    POSITION_LATERALE(7, "Réglage de la position latérale"),
    POSITION_ASSISE(8, "Réglage de la position assise");
    @Id
    @Column(name = "fonction_id")
    int id;
    @Column(name = "fonction_description")
    String description;
    @ManyToMany(mappedBy = "fonctionsLit")
    List<Lit> lits;

    FonctionLit(){}
    FonctionLit(int id, String description){
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
                return description;
    }
}
