package business.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class YdnsConfigTest {

    @Test
    void fromFile() {
        Optional<YdnsConfig> optionalYdnsConfig = YdnsConfig.fromFile();
        assertFalse(optionalYdnsConfig.isEmpty());
    }

    @Test
    void toFile() {
        List<String> domains = new ArrayList<>();
        YdnsConfig cfg = YdnsConfig.NewYdnsConfigBuilder("https://ydns.io/api/v1/update/?host=","","",domains);
        assertDoesNotThrow(cfg::toFile);
    }
}