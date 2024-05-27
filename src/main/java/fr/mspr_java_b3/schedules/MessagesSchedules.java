package fr.mspr_java_b3.schedules;

import fr.mspr_java_b3.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessagesSchedules {

    private final AnnonceService annonceService;

    @Autowired
    public MessagesSchedules(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @Scheduled(cron = "0 8 * * * ?")
    public void cronJobSch() {
        annonceService.cleanAnnoncesMessages();
    }
}
