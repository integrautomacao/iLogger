package IntegraLogger.DTO;

import IntegraLogger.Model.Plc.Plc;

public class MultipleTagReadDTO {
    private String[] tags;
    private Plc plc;

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Plc getPlc() {
        return plc;
    }

    public void setPlc(Plc plc) {
        this.plc = plc;
    }
}
