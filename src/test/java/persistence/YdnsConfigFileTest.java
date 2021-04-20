package persistence;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class YdnsConfigFileTest {

    @Test
    void prepareDefaultFile() {
        assertDoesNotThrow(YdnsConfigFile::prepareDefaultFile);
    }
}