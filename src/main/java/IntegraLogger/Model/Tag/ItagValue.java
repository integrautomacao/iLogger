package IntegraLogger.Model.Tag;

import IntegraLogger.Model.Plc.Plc;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "ItagValue")
@Table(name = "ItagValue")
public class ItagValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "valueInt")
    private Integer valueInt;

    @Column(name = "valueString")
    private String valueString;

    @Column(name = "valueBool")
    private Boolean valueBool;

    @Column(name = "valueFloat")
    private Float valueFloat;

    @Column(name = "type")
    private String type;

    @Column(name = "lastUpdate")
    private Date lastUpdate;

    @Column(name = "date")
    private String date;

    @Column(name = "hour")
    private String hour;

    @ManyToOne
    @ElementCollection(fetch = FetchType.EAGER)
    private Plc plcSource;

    public Plc getPlcSource() {
        return plcSource;
    }

    public void setPlcSource(Plc plcSource) {
        this.plcSource = plcSource;
    }

    public ItagValue() {
        this.valueString = null;
        this.valueBool = null;
        this.valueFloat = null;
        this.valueInt = null;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
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

    public Integer getValueInt() {
        return valueInt;
    }

    public void setValueInt(Integer valueInt) {
        this.valueInt = valueInt;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public Boolean getValueBool() {
        return valueBool;
    }

    public void setValueBool(Boolean valueBool) {
        this.valueBool = valueBool;
    }

    public Float getValueFloat() {
        return valueFloat;
    }

    public void setValueFloat(Float valueFloat) {
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

}
