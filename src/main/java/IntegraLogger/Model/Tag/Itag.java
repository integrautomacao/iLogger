package IntegraLogger.Model.Tag;

import etherip.Tag;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Itag")
@Table(name = "Itag")
public class Itag {
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

    @Column(name = "periodSec")
    private float periodSec;

    @Column(name = "typsse")
    private String type;

    @Column(name = "lastUpdate")
    private Date lastUpdate;

    @Column(name = "description")
    private String description;
//
//    private List<ItagListener> listeners = new ArrayList<>();
//    private Object oldValue = new Object();
//    private Tag tag;


//    public void setTag(Tag tag) throws Exception {
//        this.tag = tag;
//        lastUpdate = new Date();
//        if (tag.getData() != null) {
//            this.type = tag.getData().getType().name();
//            if (tag.getData().isNumeric()) {
//                switch (this.type) {
//                    case "BOOL":
//                        if (tag.getData().getNumber(0).intValue() == 0) {
//                            this.value = false;
//                        } else {
//                            this.value = true;
//                        }
//                    case "REAL":
//                        this.value = tag.getData().getNumber(0).floatValue();
//
//                    default:
//                        this.value = tag.getData().getNumber(0).intValue();
//                }
//            } else {
//                this.value = tag.getData().getString();
//            }
//            runListeners();
//        }
//    }
//
//    public void runListeners() {
//        if (!listeners.isEmpty()) {
//            for (ItagListener listener : listeners) {
//                listener.doAction(this);
//            }
//        }
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Object getValue() {
//        return value;
//    }
//
//    public float getPeriodSec() {
//        return periodSec;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public Date getLastUpdate() {
//        return lastUpdate;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public List<ItagListener> getListeners() {
//        return listeners;
//    }
//
//    public Object getOldValue() {
//        return oldValue;
//    }
//
//    public Tag getTag() {
//        return tag;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setValue(Object value) {
//        this.value = value;
//    }
//
//    public void setPeriodSec(float periodSec) {
//        this.periodSec = periodSec;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
