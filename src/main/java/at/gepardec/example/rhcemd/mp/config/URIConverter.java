package at.gepardec.example.rhcemd.mp.config;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.spi.Converter;

import java.net.URI;

/**
 * This is a MP-Config converter which converts a configuration property string to an {@link URI} instance.
 * Usually if the value is blank or null, a converter is supposed to return null.
 * The converter is registered via spi META-INF/services/org.eclipse.microprofile.config.spi.Converter
 */
public class URIConverter implements Converter<URI> {

    @Override
    public URI convert(String value) {
        return StringUtils.isBlank(value) ? null : URI.create(value);
    }
}
