package presentation.model;

import java.time.LocalDateTime;
import java.util.Map;

public class UpdateSummary {
    private String Timestamp;
    private String NewIp;
    private Map<String,Integer> DomainStatus;

    private UpdateSummary(){

    }

    public static UpdateSummary build(LocalDateTime timestamp, String ip, Map<String,Integer> domainStatus){
        UpdateSummary newUpdateSummary = new UpdateSummary();
        newUpdateSummary.Timestamp = timestamp.toString();
        newUpdateSummary.NewIp = ip;
        newUpdateSummary.DomainStatus = domainStatus;
        return newUpdateSummary;
    }
}
