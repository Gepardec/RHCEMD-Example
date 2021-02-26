package at.gepardec.example.rhcemd.mp.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

/**
 * This is the readiness health check.
 */
@Readiness
@ApplicationScoped
public class RhcemdReadinessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
                .name("RhcemdReadinessCheck")
                .state(true)
                .withData("checks", "none")
                .build();
    }
}
