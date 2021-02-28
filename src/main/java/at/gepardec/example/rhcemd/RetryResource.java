package at.gepardec.example.rhcemd;

import at.gepardec.example.rhcemd.mp.fault.RetryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/retry")
@RequestScoped
public class RetryResource {

    @Inject
    private RetryService retryService;

    @Path("/working")
    @GET
    public String working() {
        return retryService.works();
    }

    @Path("/fail")
    @GET
    public String fail() {
        return retryService.fails();
    }

    @Path("/noRetry")
    @GET
    public String noRetry() {
        return retryService.retryOnIllegalStateException(new IllegalArgumentException(), 1);
    }

    @Path("/retryOnWorking")
    @GET
    public String retryOnWorking() {
        return retryService.retryOnIllegalStateException(new IllegalStateException(), 1);
    }

    @Path("/retryOnFail")
    @GET
    public String retryOnFail() {
        return retryService.retryOnIllegalStateException(new IllegalStateException(), 4);
    }
}
