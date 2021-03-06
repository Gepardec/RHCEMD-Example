package at.gepardec.example.rhcemd;

import at.gepardec.example.rhcemd.mp.fault.BulkheadService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/bulkhead")
@RequestScoped
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
