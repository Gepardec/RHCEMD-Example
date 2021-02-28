package at.gepardec.example.rhcemd;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    private static final String ERROR_TEMPLATE = "Error on path: '%s' with exception '%s' and message '%s'";

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Exception exception) {
        return Response.serverError()
                .entity(String.format(ERROR_TEMPLATE,
                        uriInfo.getAbsolutePath(),
                        exception.getClass().getSimpleName(),
                        exception.getMessage()))
                .build();
    }
}
