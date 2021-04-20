package business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YdnsUpdateManagerTest {

    @Test
    void tryUpdate() {
        assertDoesNotThrow(YdnsUpdateManager::TryUpdate);
    }
}