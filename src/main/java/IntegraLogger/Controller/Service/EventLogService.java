package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.EventLogRepository;
import IntegraLogger.Model.Misc.EventLog;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EventLogService extends ServiceBase<EventLog, Long, EventLogRepository>{

//    private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public EventLogService(EventLogRepository repository) {
        super(repository);
    }

    public void logRegister(String message, String threadName){
        EventLog eventLog = new EventLog(new Date(), message, threadName);
        save(eventLog);
    }
}
