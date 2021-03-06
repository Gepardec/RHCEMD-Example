package at.gepardec.example.rhcemd.mp.fault;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class BulkheadService {

    @Bulkhead(value = 5)
    public String semaphore() {
        return execute(500);
    }

    @Asynchronous
    @Bulkhead(value = 5, waitingTaskQueue = 10)
    public CompletionStage<String> threadpool() {
        return CompletableFuture.completedFuture(execute(500));
    }

    private String execute(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "I have succeeded";
    }
}
