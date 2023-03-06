package ma.uiass.eia.pds.model.Lit;

import javax.persistence.*;

@Entity(name="t_lit")
@Inheritance(strategy = InheritanceType.JOINED)
public class Lit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lit_id")
    int numero;
    @Enumerated(EnumType.STRING)
    EtatLit etat;
    @Enumerated(EnumType.STRING)
    TypeLit type;
    public Lit(){}

    public Lit(int numero, EtatLit etat, TypeLit type) {
        this.numero = numero;
        this.etat = etat;
        this.type = type;
    }
    public Lit(EtatLit etat, TypeLit type){
        this.etat = etat;
        this.type = type;
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EtatLit getEtat() {
        return etat;
    }

    public void setEtat(EtatLit etat) {
        this.etat = etat;
    }

    public TypeLit getType() {
        return type;
    }

    public void setType(TypeLit type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Lit{" +
                "numero=" + numero +
                ", etat=" + etat +
                ", type=" + type +
                '}';
    }
}
