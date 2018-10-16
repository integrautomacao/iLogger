package IntegraLogger.Model.Tag;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Itag")
@Table(name = "Itag")
public class ItagValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "valueInt")
    private int valueInt;

    @Column(name = "valueString")
    private String valueString;

    @Column(name = "valueBool")
    private boolean valueBool;

    @Column(name = "valueFloat")
    private float valueFloat;

    @Column(name = "type")
    private String type;

    @Column(name = "lastUpdate")
    private Date lastUpdate;

    @Column(name = "description")
    private String description;

    public ItagValue(String name, String type, Date lastUpdate, String description, Object value) {
        this.name = name;
        this.type = type;
        this.lastUpdate = lastUpdate;
        this.description = description;
        switch (type) {
            case "STRUCT":
                this.valueString = value.toString();
                ....
        }

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

    public int getValueInt() {
        return valueInt;
    }

    public void setValueInt(int valueInt) {
        this.valueInt = valueInt;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public boolean isValueBool() {
        return valueBool;
    }

    public void setValueBool(boolean valueBool) {
        this.valueBool = valueBool;
    }

    public float getValueFloat() {
        return valueFloat;
    }

    public void setValueFloat(float valueFloat) {
        this.valueFloat = valueFloat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
