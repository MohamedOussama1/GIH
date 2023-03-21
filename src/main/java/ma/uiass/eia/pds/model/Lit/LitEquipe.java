package ma.uiass.eia.pds.model.Lit;

import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.dm.Dm;
import ma.uiass.eia.pds.model.espace.Espace;

import javax.persistence.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "t_lit_equipe")
public class LitEquipe extends LitItem {
    @OneToMany(mappedBy = "lit")
    List<Dm> lstDm = new ArrayList<>();
    public LitEquipe(){}

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
