package IntegraLogger.Model.Misc;

import javax.persistence.*;
import java.util.Date;

@Table(name = "EventLog")
@Entity(name = "EventLog")
public class EventLog {
    @Column(name = "datetime")
    private Date dateTime;
    @Column(name = "logMessage")
    private String logMessage;
    @Column(name = "threadName")
    private String threadName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public EventLog(Date dateTime, String logMessage, String threadName) {
        this.dateTime = dateTime;
        this.logMessage = logMessage;
        this.threadName = threadName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
