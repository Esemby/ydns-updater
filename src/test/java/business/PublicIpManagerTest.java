package business;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PublicIpManagerTest {

    @Test
    void getPublicIp() {
        Optional<String> publicIp = PublicIpManager.GetPublicIp();
        assertFalse(publicIp.isEmpty());
    }
}