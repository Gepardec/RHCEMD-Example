package at.gepardec.example.rhcemd.mp.fault;

import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Retry;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

@Dependent
public class AsynchronousService {

    /**
     * The execution occurs on another Thread but request scoped beans work anyway.
     */
    @Inject
    private RequestScopedService requestScopedService;

    private int count = 0;

    @Asynchronous
    public CompletionStage<String> workingCompletionStage() {
        return CompletableFuture.completedFuture(requestScopedService.doStuff());
    }

    /**
     * Retry works because we use CompletionStage as the return type which allows chained executions,
     * which is necessary to invoke the other fault-tolerance mechanisms.
     */
    @Retry
    @Asynchronous
    public CompletionStage<String> workingCompletionStageWithRetry() {
        count++;
        return CompletableFuture.failedFuture(new IllegalArgumentException("Failed '" + count + "' time"));
    }

    @Asynchronous
    public CompletionStage<String> failCompletionStage() {
        throw new IllegalArgumentException("I did fail");
    }

    @Asynchronous
    public Future<String> workingFuture() {
        return ConcurrentUtils.constantFuture(requestScopedService.doStuff());
    }

    @Asynchronous
    public Future<String> failFuture() {
        throw new IllegalArgumentException("I did fail");
    }

    /**
     * Retry won't work a failed Future, only on CompletionStage.
     * No other fault-tolerance mechanism will be invoked if the return type is a future and has failed.
     * <br/>
     * This is because CompletionStage provides thenAccept() Methods which allows chaining of executions, which Future doesn't.
     */
    @Retry
    @Asynchronous
    public Future<String> failFutureWithFailedRetry() {
        count++;
        return CompletableFuture.failedFuture(new IllegalArgumentException("Failed '" + count + "' time"));
    }
}
