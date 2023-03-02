package ma.uiass.eia.pds;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.model.Lit;
import ma.uiass.eia.pds.service.LitService;
import ma.uiass.eia.pds.service.LitServiceImpl;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    private LitService litService = new LitServiceImpl();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        List<String> list = new ArrayList<>();
        List<Lit> lits = litService.getLits();
        lits.forEach(elt -> list.add(elt.toString()));
        return Response.ok().entity(list).build();
    }
}
