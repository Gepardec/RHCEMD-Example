package at.gepardec.example.rhcemd.mp.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Map;

public class CustomConfigSource implements ConfigSource {

    @Override
    public Map<String, String> getProperties() {
        return null;
    }

    @Override
    public String getValue(String propertyName) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
