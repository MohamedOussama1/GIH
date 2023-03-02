package ma.uiass.eia.pds;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.model.EtatLit;
import ma.uiass.eia.pds.model.Lit;
import ma.uiass.eia.pds.model.TypeLit;
import ma.uiass.eia.pds.service.LitService;
import ma.uiass.eia.pds.service.LitServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Path("lits")
public class LitController {

    private LitService litService = new LitServiceImpl();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLits(){
        List<String> lits = new ArrayList<>();
        litService.getLits().forEach(elt -> lits.add(elt.toString()));
        return Response
                .ok()
                .entity(lits)
                .build();
    }
    @POST
    @Path("/{etat}/{type}")
    public Response postLit(@PathParam(value = "etat") String etatLit,@PathParam(value = "type") String typeLit){
        litService.addLit(new Lit(EtatLit.fromString(etatLit), TypeLit.fromString(typeLit)));
        return Response.ok().build();
    }
}
