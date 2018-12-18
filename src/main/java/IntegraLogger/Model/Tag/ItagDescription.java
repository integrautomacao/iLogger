package IntegraLogger.Model.Tag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
