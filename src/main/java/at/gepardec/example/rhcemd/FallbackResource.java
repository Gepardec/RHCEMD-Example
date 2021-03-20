package at.gepardec.example.rhcemd;

import at.gepardec.example.rhcemd.mp.fault.FallbackService;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/fallback")
@RequestScoped
@Counted(name = "example-resource", absolute = true)
public class FallbackResource {

    @Inject
    private FallbackService fallbackService;

    @Path("/method")
    @GET
    public String workingMethod() {
        return fallbackService.fallbackMethod();
    }

    @Path("/bean")
    @GET
    public String workingBean() {
        return fallbackService.fallbackBean();
    }

    @Path("/applyOnWorking")
    @GET
    public String applyOnWorking() {
        return fallbackService.applyOnIllegalStateException(new IllegalStateException(""));
    }

    @Path("/applyOnFail")
    @GET
    public String applyOnFail() {
        return fallbackService.applyOnIllegalStateException(new IllegalArgumentException(""));
    }
}
