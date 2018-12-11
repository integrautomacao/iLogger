package IntegraLogger.Model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Sector")
@Table(name = "Sector")
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "level")
    private int level;

    @OneToMany
    @Column(name = "Users")
    private List<Usuario> users = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public Long getId() {
        return id;
    }
}
