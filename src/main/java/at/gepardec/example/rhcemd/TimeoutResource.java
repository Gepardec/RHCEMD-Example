package at.gepardec.example.rhcemd;

import at.gepardec.example.rhcemd.mp.fault.TimeoutService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/timeout")
@RequestScoped
public class TimeoutResource {

    @Inject
    private TimeoutService timeoutService;

    @Path("/working")
    @GET
    public String workingTimeout() {
        return timeoutService.working();
    }

    @Path("/broken")
    @GET
    public String brokenTimeout() {
        return timeoutService.broken() + " (Do you have also the felling that the execution took to long?)";
    }

    @Path("/configured")
    @GET
    public String configurableTimeout() {
        return timeoutService.configured();
    }
}
