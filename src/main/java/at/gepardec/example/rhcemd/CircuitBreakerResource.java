package at.gepardec.example.rhcemd;

import at.gepardec.example.rhcemd.mp.fault.CircuitBreakerServiceTimerTask;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Timer;

@Path("/circuitbreaker")
@RequestScoped
@Counted(name = "example-resource", absolute = true)
public class CircuitBreakerResource {

    @Inject
    private CircuitBreakerServiceTimerTask timerTask;

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response get() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 100);

        while (!timerTask.isDone()) {
            Thread.onSpinWait();
        }

        return Response.ok().entity(timerTask.getResult()).build();
    }
}
