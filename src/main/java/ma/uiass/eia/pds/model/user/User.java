package ma.uiass.eia.pds.model.user;


import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(name = "user_password", nullable = false)
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
