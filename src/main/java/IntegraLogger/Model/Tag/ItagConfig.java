package IntegraLogger.Model.Tag;

import IntegraLogger.Application.Listeners.ListenersIndex;
import etherip.TagListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<ListenersIndex> listeners = new ArrayList<>();

    public ItagConfig(String name, String description, int timeUpdate) {
        this.name = name;
        this.description = description;
        this.timeUpdate = timeUpdate;
    }

    public ItagConfig() {
    }

    public List<ListenersIndex> getListeners() {
        return listeners;
    }

    public boolean removeListener(ListenersIndex listenersIndex) {
        if (listeners.contains(listenersIndex)) {
            listeners.remove(listenersIndex);
            return true;
        } else {
            return false;
        }
    }

    public void setListener(ListenersIndex listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
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
