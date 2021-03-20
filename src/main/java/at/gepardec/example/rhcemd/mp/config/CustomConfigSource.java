package at.gepardec.example.rhcemd.mp.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Map;
import java.util.Set;

public class CustomConfigSource implements ConfigSource {

    private static final Map<String, String> CONFIG = Map.of(
            "customConfigProperty", "customConfigPropertyValue"
    );

    @Override
    public Map<String, String> getProperties() {
        return CONFIG;
    }

    @Override public Set<String> getPropertyNames() {
        return CONFIG.keySet();
    }

    @Override
    public String getValue(String propertyName) {
        return CONFIG.get(propertyName);
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrdinal() {
        return 99; // microprofile-config.properties is '100' and we want to be after that
    }
}
