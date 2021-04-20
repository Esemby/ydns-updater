package business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class PublicIpManager {

    private static final HttpClient ipGetClient;
    private static final HttpRequest request;
    private static final Logger logger = LogManager.getLogger(PublicIpManager.class);

    static {
        //todo make redirect auto follow
        ipGetClient = HttpClient
                .newBuilder()
                .build();

        request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://myexternalip.com/raw"))
                .GET()
                .build();
    }

    public static Optional<String> GetPublicIp(){
        try {
            HttpResponse<String> response = ipGetClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                return Optional.of(response.body());
            } else {
                logger.error("Could not get public IP");
                return Optional.empty();
            }
        } catch (IOException | InterruptedException ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
