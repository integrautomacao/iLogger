package IntegraLogger.Model.Plc;


import IntegraLogger.Model.Tag.ItagConfig;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Plc")
@Table(name = "Plc")
public class Plc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "ip")
    private String ip;
    @Column(name = "slot")
    private int slot;
    @Column(name = "description")
    private String description;

    @OneToMany
    @Column(name = "itagConfigs")
    private List<ItagConfig> itagConfigs = new ArrayList<>();

    public String getIp() {
        return ip;
    }

    public List<ItagConfig> getItagConfigs() {
        return itagConfigs;
    }

    public Long getId() {
        return id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
