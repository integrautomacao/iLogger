package IntegraLogger.Model.Tag;

import javax.persistence.*;

@Entity(name = "ItagDescription")
@Table(name = "ItagDescription")
public class ItagDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ItagDescription(String value) {
        this.value = value;
    }

    public ItagDescription() {
    }

    @Column(name = "value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
