package IntegraLogger.Model.Plc;


import javax.persistence.*;

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
