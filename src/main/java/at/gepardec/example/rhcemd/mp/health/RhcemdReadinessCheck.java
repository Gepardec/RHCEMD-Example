package at.gepardec.example.rhcemd.mp.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

/**
 * This is the readiness health check, determining if the service is ready to accept connections.
 * If this check fails the service will not get any request to serve
 */
@Readiness
@ApplicationScoped
public class RhcemdReadinessCheck implements HealthCheck {

    /**
     * In case of an exception, the health implementation will return an Http-Status: 503, as specified
     */
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
                .up()
                .name("RhcemdReadinessCheck") // A name a consumer can use
                .withData("checks", "none") // Key-value pairs for the data part fo the response
                .build();
    }
}
