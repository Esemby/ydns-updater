package business;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class YdnsUpdateClientTest {

    @Test
    void update() {
        Map<String,Integer> domainStatus = YdnsUpdateClient.update();
        for (String domain :
                domainStatus.keySet()) {
            Integer statusCode = domainStatus.get(domain);
            String failMessage = domain + ":" + statusCode;
            assertEquals(statusCode, 200, failMessage);
        }
    }
}