package ma.uiass.eia.pds.model.dm;

import org.json.JSONObject;

import javax.persistence.*;

@Entity(name="t_typedm")
public class TypeDM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typedm_id")
    private int id;
    @Column(name = "typedm_nom")
    private String nomType;


    @Column(name = "categorie")
    String categorie;

    public TypeDM (){};

    public TypeDM(String nomType, String categorie) {
        this.nomType = nomType;
        this.categorie = categorie;
    }


    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }



    public JSONObject toJsonn(){
        JSONObject jo=new JSONObject();
        jo.put("id_typedm",id);
        jo.put("nomType",nomType);
        jo.put("categorie", categorie);

        return jo;
    }


    @Override
    public String toString() {
        return "TypeDM{" +
                "id=" + id +
                ", nomType='" + nomType + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}