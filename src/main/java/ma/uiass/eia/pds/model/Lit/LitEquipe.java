package ma.uiass.eia.pds.model.Lit;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.dm.Dm;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "t_lit_equipe")
public class LitEquipe extends Lit {
    @OneToMany(mappedBy = "lit")
    List<Dm> lstDm = new ArrayList<>();
    public LitEquipe(){}

    public List<Dm> getLstDm() {
        return lstDm;
    }

    public void setLstDm(List<Dm> lstDm) {
        this.lstDm = lstDm;
    }

    public LitEquipe(List<Dm> lstDm) {
        this.lstDm = lstDm;
    }

    public LitEquipe(int numero, EtatLit etat, TypeLit type, List<Dm> lstDm) {
        super(numero, etat, type);
        this.lstDm = lstDm;
    }

    public LitEquipe(EtatLit etat, TypeLit type, List<Dm> lstDm) {
        super(etat, type);
        this.lstDm = lstDm;
    }
}
