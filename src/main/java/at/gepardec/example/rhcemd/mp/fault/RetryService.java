package at.gepardec.example.rhcemd.mp.fault;

import org.eclipse.microprofile.faulttolerance.Retry;

import javax.enterprise.context.Dependent;

@Dependent
public class RetryService {

    // Bad to have a state here, but this is just for illustration
    private int count = 0;

    @Retry(maxRetries = 3, delay = 500, jitter = 50)
    public String works() {
        if (count < 2) {
            count++;
            throw new IllegalArgumentException("Retry failed the '" + count + "' time");
        }
        return "Retry worked";
    }

    @Retry(maxRetries = 3, delay = 500, jitter = 50)
    public String fails() {
        if (count < 4) {
            count++;
            throw new IllegalArgumentException("Retry failed the '" + count + "' time");
        }
        return "Retry worked but it shouldn't";
    }

    @Retry(maxRetries = 3, delay = 500, jitter = 50, retryOn = IllegalStateException.class)
    public String retryOnIllegalStateException(RuntimeException e, int i) {
        if (count < i) {
            count++;
            throw e;
        }
        return "Retry worked";
    }
}
