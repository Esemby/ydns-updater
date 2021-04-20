package presentation;

import business.YdnsUpdateManager;
import business.model.UpdateEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import presentation.model.UpdateSummary;

import java.time.LocalDateTime;

public class ConsoleIO {
    public static class DisplayDomainStatusAction implements UpdateEvent {
        @Override
        public void onUpdate() {
            UpdateSummary summary = UpdateSummary.build(
                    LocalDateTime.now(), YdnsUpdateManager.getLastPublicIp(),YdnsUpdateManager.getDomainStatus()
            );
            Gson gson = new GsonBuilder()
                    .disableHtmlEscaping()
                    .setPrettyPrinting()
                    .create();
            System.out.println(gson.toJson(summary));
        }
    }

    public static UpdateEvent DisplayDomainStatusAction(){
        return new DisplayDomainStatusAction();
    }
}
