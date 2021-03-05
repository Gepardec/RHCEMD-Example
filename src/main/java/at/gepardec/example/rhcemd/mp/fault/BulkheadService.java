package at.gepardec.example.rhcemd.mp.fault;

import org.eclipse.microprofile.faulttolerance.Bulkhead;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BulkheadService {

    @Bulkhead(value = 5)
    public String semaphore() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "I have succeeded";
    }
}
