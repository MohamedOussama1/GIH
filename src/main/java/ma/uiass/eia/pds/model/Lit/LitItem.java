package ma.uiass.eia.pds.model.Lit;

import javax.persistence.*;

@Entity(name = "t_lit_item")
public class LitItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lit_item_id")
    int id;
    @Column(name = "lit_item_code")
    String code;
    @ManyToOne
    @JoinColumn(name = "description_lit_id", referencedColumnName = "lit_id")
    Lit litDescription;

    public LitItem(){}
    public LitItem(String code, Lit litDescription) {
        this.code = code;
        this.litDescription = litDescription;
    }
    public LitItem(Lit litDescription){
        this.litDescription = litDescription;
    }
}
