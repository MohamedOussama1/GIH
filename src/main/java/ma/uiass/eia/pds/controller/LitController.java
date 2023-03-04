package ma.uiass.eia.pds.controller;

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
        litService
                .getLits()
                .forEach(elt -> lits.add(elt.toString()));
        return Response
                .ok()
                .entity(lits)
                .build();
    }
    @GET
    @Path("/{etat}")
    public Response getLitsByEtat(@PathParam(value = "etat") String etatLit){
        List<String> lits = new ArrayList<>();
        litService
                .getLitsByEtat(etatLit)
                .forEach(elt -> lits.add(elt.toString()));
        return Response
                .ok()
                .entity(lits)
                .build();
    }
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/{etat}/{type}")
    public Response postLit(@PathParam(value = "etat") String etatLit,@PathParam(value = "type") String typeLit){
            litService.addLit(new Lit(EtatLit.fromString(etatLit), TypeLit.fromString(typeLit)));
            return Response
                    .ok()
                    .build();
        }
    @PUT
    @Path("/{id}/{etat}/{type}")
    public Response modifyLit(@PathParam(value = "id") int id, @PathParam(value = "etat") String etatLit, @PathParam(value = "type") String typeLit){
            litService.updateLit(id, EtatLit.fromString(etatLit), TypeLit.fromString(typeLit));
            return Response
                    .ok()
                    .build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteLit(@PathParam(value = "id") int id){
        litService.deleteLit(id);
        return Response
                .ok()
                .build();
    }
}
