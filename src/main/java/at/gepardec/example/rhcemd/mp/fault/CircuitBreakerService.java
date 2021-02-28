package at.gepardec.example.rhcemd.mp.fault;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

import javax.enterprise.context.Dependent;
import java.time.temporal.ChronoUnit;

@Dependent
public class CircuitBreakerService {

    @CircuitBreaker(requestVolumeThreshold = 10, failureRatio = 0.5, successThreshold = 5, delay = 550, delayUnit = ChronoUnit.MILLIS)
    public String doStuff(boolean fail) {
        if (fail) {
            throw new IllegalArgumentException("Failed");
        }
        return "I am the result";
    }
}
