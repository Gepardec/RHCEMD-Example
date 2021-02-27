package at.gepardec.example.rhcemd.mp.fault;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.enterprise.context.Dependent;
import java.time.temporal.ChronoUnit;

@Dependent
public class TimeoutService {

    /**
     * This example works because we check the interrupted state of the Thread each iteration, otherwise
     * it wouldn't work as with the brokenTimeout example.
     */
    @Timeout(value = 1, unit = ChronoUnit.MILLIS)
    @Fallback(fallbackMethod = "timeoutFallback")
    public String working() {
        double value = 10.0;
        for (long i = 1; i <= 100_000_000_000L; i++) {
            if (Thread.interrupted()) {
                break;
            }
            value = (value * i * 100) / 10;
        }

        return "I have not timeout";
    }

    /**
     * This example illustrates the problem with Timeout, when a Thread doesn't wait of doesn't checks it interrupted state.
     */
    @Timeout(value = 1, unit = ChronoUnit.MILLIS)
    @Fallback(fallbackMethod = "timeoutFallback")
    public String broken() {
        double value = 10.0;
        for (long i = 1; i <= 100_000_000L; i++) {
            value = (value * i * 100) / 10;
        }

        return "I have not timeout";
    }

    /**
     * This set values are overwritten in 'META-INF/microprofile-config.properties'
     */
    @Timeout
    @Fallback
    public String configured() {
        double value = 10.0;
        for (long i = 1; i <= 100_000_000L; i++) {
            if (Thread.interrupted()) {
                break;
            }
            value = (value * i * 100) / 10;
        }

        return "I have not timeout";
    }

    /**
     * This method returns the default result in a case of an timeout
     */
    String timeoutFallback() {
        return "Ups, I have timeout";
    }

    String overwrittenTimeoutFallback() {
        return "Ups, I have timeout. This is the dynamic defined fallback method";
    }
}
