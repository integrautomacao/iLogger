package IntegraLogger.Application;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@Controller
public class ThreadPool {

    private List<Thread> threads = new ArrayList<>();
    private List<PlcThread> plcThreads = new ArrayList<>();

    public void addThread(Thread thread){
        if (!threads.contains(thread)){
            threads.add(thread);
        }
    }

    public void addPlcThread(PlcThread plcThread){
        if (!plcThreads.contains(plcThread)){
            plcThreads.add(plcThread);
        }
    }

    @Scheduled(fixedRate = 5000)
    private void threadMonitor(){
        for (Thread thread : threads){
            if (!thread.isAlive()){
                System.out.println("Thread "+ thread.getName()+" is not alive. Trying reconnect...");
                for (PlcThread plcThread : plcThreads){
                    if (plcThread.getPlc().getDescription().equals(thread.getName())){
                        plcThreads.remove(plcThread);
                        PlcThread newPlcThread = new PlcThread(plcThread.getPlc());
                        plcThreads.add(newPlcThread);
                        threads.remove(thread);
                        Thread newThread = new Thread(newPlcThread, newPlcThread.getPlc().getDescription());
                        threads.add(newThread);
                        newThread.start();
                    }
                }
            }
        }
    }

}
