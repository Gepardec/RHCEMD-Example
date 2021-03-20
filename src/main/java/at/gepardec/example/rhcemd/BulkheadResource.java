package at.gepardec.example.rhcemd;

import at.gepardec.example.rhcemd.mp.fault.BulkheadService;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/bulkhead")
@RequestScoped
@Counted(name = "example-resource", absolute = true)
public class BulkheadResource {

    @Inject
    private BulkheadService service;

    @Path("/semaphore")
    @GET
    public String semaphore() {
        return service.semaphore();
    }

    @Path("/threadpool")
    @GET
    public String threadpool() throws Exception {
        return service.threadpool().toCompletableFuture().get();
    }
}
