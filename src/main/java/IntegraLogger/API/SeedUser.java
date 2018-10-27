package IntegraLogger.API;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public final class SeedUser {

    @EventListener(ApplicationReadyEvent.class)
    public static void teste(){
        System.out.println("acabou o startup");
    }
}
