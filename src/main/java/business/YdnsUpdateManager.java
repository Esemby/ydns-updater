package business;

import business.model.UpdateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.*;

public class YdnsUpdateManager {

    private static final long updateInterval;
    private static final List<UpdateEvent> updateEvents;
    private static final Logger logger = LogManager.getLogger(YdnsUpdateManager.class);
    private static LocalTime lastUpdate;
    private static String lastPublicIp;
    private static Map<String,Integer> domainStatus;

    static {
        updateInterval = 300;
        updateEvents = new ArrayList<>();
        lastUpdate = LocalTime.now().minusSeconds(updateInterval);
        lastPublicIp = "127.0.0.1";
        domainStatus = new HashMap<>();
    }

    public static void TryUpdate(){
        if(LocalTime.now().isAfter(lastUpdate.plusSeconds(updateInterval))){
            logger.info("Update Interval reached ("+LocalTime.now()+")");
            Optional<String> optionalCurrentIp = PublicIpManager.GetPublicIp();
            if(optionalCurrentIp.isPresent()){
                logger.debug("found current IP");
                String currentIp = optionalCurrentIp.get();
                if(!currentIp.contentEquals(lastPublicIp)){
                    logger.info("current IP is different, updating domains...");
                    lastPublicIp = currentIp;
                    lastUpdate = LocalTime.now();
                    domainStatus = YdnsUpdateClient.update();
                    logger.info("Domains Updated");
                    InvokeUpdateEvents();
                }
            } else{
                logger.error("current IP is empty");
            }
        }
    }

    private static void InvokeUpdateEvents(){
        updateEvents.forEach(UpdateEvent::onUpdate);
    }

    public static Map<String, Integer> getDomainStatus() {
        return domainStatus;
    }

    public static String getLastPublicIp() {
        return lastPublicIp;
    }

    public static void addUpdateEvent(UpdateEvent updateEvents) {
        YdnsUpdateManager.updateEvents.add(updateEvents);
    }

}
