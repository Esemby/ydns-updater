package business;

import business.model.YdnsConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class YdnsUpdateClient {

    private static final HttpClient httpClient;
    private static final Logger logger = LogManager.getLogger(YdnsUpdateClient.class);
    static {
        httpClient = HttpClient
                .newBuilder()
                .build();
    }

    /**
     *
     * @return A map of the domains and their matching status codes
     */
    public static Map<String,Integer> update() {
        Optional<YdnsConfig> optionalCfg = YdnsConfig.fromFile();
        if(optionalCfg.isPresent()){
            YdnsConfig cfg = optionalCfg.get();
            return update(cfg);
        } else {
            logger.error("optional YDNS config was empty");
            return new HashMap<>();
        }
    }

    /**
     *
     * @param cfg A valid YdnsConfig
     * @return A map of the domains and their matching status codes
     */
    public static Map<String,Integer> update(YdnsConfig cfg) {
        Map<String,Integer> responseCodes = new HashMap<>();
        String header = getAuthHeader(cfg.getUsername(), cfg.getSecret());
        for (String domain :
                cfg.getDomains()) {
            URI uri = URI.create(cfg.getApi() + domain);
            HttpRequest request = getApiRequest(uri,header);
            int statusCode = 500;
            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                statusCode = response.statusCode();
            } catch (IOException | InterruptedException ex) {
                logger.error(ex.getMessage());
                ex.printStackTrace();
            }
            responseCodes.put(domain,statusCode);
        }
        return responseCodes;
    }

    private static String getAuthHeader(String username, String secret){
        String auth = username + ":" + secret;
        String auth64 = Base64.getEncoder().encodeToString(auth.getBytes());
        return  "Basic " + auth64;
    }

    private static HttpRequest getApiRequest(URI uri, String header) {
        return HttpRequest
                .newBuilder(uri)
                .setHeader("Authorization" ,header)
                .GET()
                .build();
    }
}
