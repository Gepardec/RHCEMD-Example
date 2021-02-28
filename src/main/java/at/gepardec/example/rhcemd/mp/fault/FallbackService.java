package at.gepardec.example.rhcemd.mp.fault;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import javax.enterprise.context.Dependent;

@Dependent
public class FallbackService {

    /**
     * This is fallback handler which is not a CDI bean!!!
     */
    public static class CustomFallbackHandler implements FallbackHandler<String> {

        @Override
        public String handle(ExecutionContext context) {
            return "I am the fallback handler result. Exception: " + context.getFailure().getClass().getSimpleName()
                    + ", Message: " + context.getFailure().getMessage()
                    + ", Method: " + context.getMethod().getName();
        }
    }

    @Fallback(fallbackMethod = "fallback")
    public String fallbackMethod() {
        throw new IllegalArgumentException();
    }

    @Fallback(value = CustomFallbackHandler.class)
    public String fallbackBean() {
        throw new IllegalArgumentException();
    }

    /**
     * Fallback methods need to have the same signature as the method the fallback was defined on.
     */
    @Fallback(fallbackMethod = "fallbackWithParameter", applyOn = IllegalStateException.class)
    public String applyOnIllegalStateException(RuntimeException e) {
        throw e;
    }

    private String fallback() {
        return "I am the fallback method result";
    }

    private String fallbackWithParameter(RuntimeException e) {
        return "I am the fallback method with parameters result. Exception: " + e.getClass().getSimpleName();
    }
}
