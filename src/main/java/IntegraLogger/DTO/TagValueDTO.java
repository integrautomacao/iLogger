package IntegraLogger.DTO;

public class TagValueDTO {
    private String plc;
    private String tagName;
    private String type;
    private String value;

    public TagValueDTO(String tagName, String type) {
        this.tagName = tagName;
        this.type = type;
    }

    public String getPlc() {
        return plc;
    }

    public void setPlc(String plc) {
        this.plc = plc;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
