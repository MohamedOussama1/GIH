package ma.uiass.eia.pds.model.dm;
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
        private DM dm;

        public DMItem() {}

        public DMItem(String code, DM dm) {
            this.code = code;
            this.dm = dm;
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
    }

