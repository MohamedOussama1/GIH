package ma.uiass.eia.pds.model.Lit;

import ma.uiass.eia.pds.model.dm.Dm;
import ma.uiass.eia.pds.model.espace.Espace;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "t_lit_equipe")
public class LitEquipe extends LitItem {
    @OneToMany(mappedBy = "lit")
    List<Dm> lstDm = new ArrayList<>();
    public LitEquipe(){}

    public LitEquipe(List<Dm> lstDm) {
        this.lstDm = lstDm;
    }

    public LitEquipe(String code, Lit litDescription, Espace espace, List<Dm> lstDm) {
        super(code, litDescription, espace);
        this.lstDm = lstDm;
    }

    public LitEquipe(String code, Lit litDescription, List<Dm> lstDm) {
        super(code, litDescription);
        this.lstDm = lstDm;
    }

    public LitEquipe(Lit litDescription, List<Dm> lstDm) {
        super(litDescription);
        this.lstDm = lstDm;
    }

    public Espace getEspace() {
        return espace;
    }

    public void setEspace(Espace espace) {
        this.espace = espace;
    }

    public List<Dm> getLstDm() {
        return lstDm;
    }

    public void setLstDm(List<Dm> lstDm) {
        this.lstDm = lstDm;
    }
}
