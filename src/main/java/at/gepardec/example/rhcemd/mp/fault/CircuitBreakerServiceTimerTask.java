package at.gepardec.example.rhcemd.mp.fault;

import org.slf4j.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.*;

/**
 * This class implements the scenario to illustrate the CircuitBreaker mechanism.
 */
@Dependent
public class CircuitBreakerServiceTimerTask extends TimerTask {

    public static class Result {

        private Map<String, List<Integer>> failedCalls = new HashMap<>();

        private List<Integer> successCalls = new LinkedList<>();

        public void registerFailedCall(String exception, int call) {
            failedCalls.computeIfAbsent(exception, (e) -> new ArrayList<>());
            failedCalls.get(exception).add(call);
        }

        public void registerSuccessCall(int call) {
            successCalls.add(call);
        }

        public Map<String, List<Integer>> getFailedCalls() {
            return failedCalls;
        }

        public List<Integer> getSuccessCalls() {
            return successCalls;
        }
    }

    @Inject
    private CircuitBreakerService service;

    @Inject
    private Logger log;

    private int count = 0;

    private Result internalResult = new Result();

    private Result result;

    @Override
    public void run() {
        count++;
        try {
            // 1.) 1-5   fail with an error
            // 2.) 6-10  fail because of the circuit being open for 550 millis
            // 3.) 11-15 work because circuit is half-open
            // 4.) 16-20 work because circuit is closed
            // 5.) 21-25 fail because of an error
            // 6.) 26-30 fail because of the circuit being open for 550 millis
            // 7.) 31-35 work because circuit is half-open
            // 4.) 36-50 work because circuit is closed
            service.doStuff(count <= 5 || (count > 20 && count <= 25)); // Fail calls '1-5' and '21-25'
            internalResult.registerSuccessCall(count);
        } catch (Exception e) {
            internalResult.registerFailedCall(e.getClass().getSimpleName(), count);
            log.error("CircuitBreakerService call '" + count + "' failed. exception: " + e.getClass().getSimpleName());
        }

        if (count == 50) {
            cancel();
            result = internalResult;
        }
    }

    public boolean isDone() {
        return result != null;
    }

    public Object getResult() {
        return result;
    }
}
