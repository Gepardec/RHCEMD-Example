package at.gepardec.example.rhcemd;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.net.URI;

/**
 * This is the configuration class which gets the configurations injected and makes them available to the application,
 * so that configurations are not spread over multiple classes.
 */
@Dependent
public class ApplicationConfig {

    @Inject
    @ConfigProperty(name = "customConfigProperty")
    private String customConfigProperty;

    @Inject
    @ConfigProperty(name = "configFileProperty")
    private String configFileProperty;

    @Inject
    @ConfigProperty(name = "undefinedProperty", defaultValue = "I am the default value")
    private String undefinedProperty;

    @Inject
    @ConfigProperty(name = "googleSearchUri", defaultValue = "http://www.google.at")
    private URI googleSearchUri;

    public String getCustomConfigProperty() {
        return customConfigProperty;
    }

    public String getConfigFileProperty() {
        return configFileProperty;
    }

    public String getUndefinedProperty() {
        return undefinedProperty;
    }

    public URI getGoogleSearchUri() {
        return googleSearchUri;
    }

    @Override
    public String toString() {
        return "ApplicationConfig{" +
                "customConfigProperty='" + customConfigProperty + '\'' +
                ", configFileProperty='" + configFileProperty + '\'' +
                ", undefinedProperty='" + undefinedProperty + '\'' +
                ", googleSearchUri=" + googleSearchUri +
                '}';
    }
}
