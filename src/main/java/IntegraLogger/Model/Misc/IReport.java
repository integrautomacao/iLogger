package IntegraLogger.Model.Misc;

import IntegraLogger.Model.Tag.ItagValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IReport {
    private List<ItagValue> events = new ArrayList<>();
    private List<String> fields = new ArrayList<>();
    private Date date;

    public IReport(Date date) {
        this.date = date;
    }

    public List<ItagValue> getEvents() {
        return events;
    }

    public void setEvents(List<ItagValue> events) {
        this.events = events;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
