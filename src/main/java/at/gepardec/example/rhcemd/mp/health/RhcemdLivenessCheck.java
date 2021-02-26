package at.gepardec.example.rhcemd.mp.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

/**
 * This is the liveness health check.
 */
@Liveness
@ApplicationScoped
public class RhcemdLivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
                .name("RhcemdLivenessCheck")
                .state(true)
                .withData("checks", "none")
                .build();
    }
}
