package ma.uiass.eia.pds.model.dm;

import ma.uiass.eia.pds.model.espace.Espace;
import org.json.JSONObject;

import javax.persistence.*;
@Entity(name = "t_dm_item")
public class DMItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "dm_item_id")
        int id;
        @Column(name = "dm_item_code")
        String code;

        @ManyToOne
        @JoinColumn(name = "dm_id")
         DM dm;

        @ManyToOne
        @JoinColumn(name = "espace_id")
        Espace espace;

        public DMItem() {}

        public DMItem(String code, DM dm) {
            this.code = code;
            this.dm = dm;
        }

        public Espace getEspace() {
            return espace;
        }

        public void setEspace(Espace espace) {
            this.espace = espace;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public DM getDm() {
            return dm;
        }

        public void setDm(DM dm) {
            this.dm = dm;
        }


        public JSONObject toJsonn(){
            JSONObject jo=new JSONObject();
            jo.put("id_dmitem",id);
            jo.put("code",code);
            jo.put("dm",dm.toJsonn());
            jo.put("espace",espace.toJsonn());
            return jo;
        }

        @Override
        public String toString() {
            return "DMItem{" +
                    "id=" + id +
                    ", code='" + code + '\'' +
                    ", dm=" + dm +
                    ", espace=" + espace +

                    '}';
        }
    }

