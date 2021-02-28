package at.gepardec.example.rhcemd;

import at.gepardec.example.rhcemd.mp.fault.AsynchronousService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/async")
@RequestScoped
public class AsynchronousResource {

    @Inject
    private AsynchronousService asynchronousService;

    @Path("/completionStage/working")
    @GET
    public String completionStageWorking() throws Exception {
        return asynchronousService.workingCompletionStage()
                .toCompletableFuture()
                .get();
    }

    @Path("/completionStage/fail")
    @GET
    public String completionStageFail() throws Exception {
        return asynchronousService.failCompletionStage()
                .exceptionally(e -> "Failed because of thrown exception: " + e.getCause().getClass().getSimpleName())
                .toCompletableFuture()
                .get();
    }

    @Path("/completionStage/workingRetry")
    @GET
    public String completionStageWorkingRetry() throws Exception {
        return asynchronousService.workingCompletionStageWithRetry()
                .toCompletableFuture()
                .get();
    }

    @Path("/future/working")
    @GET
    public String futureWorking() throws Exception {
        return asynchronousService.workingFuture()
                .get();
    }

    @Path("/future/fail")
    @GET
    public String futureFail() throws Exception {
        return asynchronousService.failFuture().get();
    }

    @Path("/future/failedRetry")
    @GET
    public String futureFailedRetry() throws Exception {
        return asynchronousService.failFutureWithFailedRetry().get();
    }
}
