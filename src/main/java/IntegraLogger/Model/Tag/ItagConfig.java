package IntegraLogger.Model.Tag;

import javax.persistence.*;

@Entity(name = "ItagConfig")
@Table(name = "ItagConfig")
public class ItagConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "timeUpdate")
    private int timeUpdate;

    public ItagConfig(String name, String description, int timeUpdate) {
        this.name = name;
        this.description = description;
        this.timeUpdate = timeUpdate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(int timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}
