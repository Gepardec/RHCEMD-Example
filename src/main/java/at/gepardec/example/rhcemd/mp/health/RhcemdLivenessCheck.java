package at.gepardec.example.rhcemd.mp.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

/**
 * This is the liveness health check, determining if the service is alive.
 * If this check fails the service will killed and restarted.
 * This is the difference between an readiness and liveness check.
 */
@Liveness
@ApplicationScoped
public class RhcemdLivenessCheck implements HealthCheck {

    /**
     * In case of an exception, the health implementation will return an Http-Status: 503, as specified
     */
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
                .name("RhcemdLivenessCheck")
                .state(true)
                .withData("checks", "none")
                .build();
    }
}
