package ma.uiass.eia.pds.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.LitService;
import ma.uiass.eia.pds.metier.LitServiceImpl;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.reservation.Reservation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("lits")
public class LitController {

    private LitService litService = new LitServiceImpl();
    @GET
    @Path("stock")
    public Response getLitsStock(
    ){
        List<String> lits = new ArrayList<>();
        litService
                .getLitsStock()
                .forEach(lit -> {
                    JSONObject jsonLit = new JSONObject(lit);
                    lits.add(jsonLit.toString());
                });
        return Response
                .ok()
                .entity(lits)
                .build();
    }
    @GET
    @Path("fonctions")
    public Response getFonctionsLit(){
        List<String> fonctionsLits = litService.getFonctionsLit();
        return Response
                .ok()
                .entity(fonctionsLits)
                .build();
    }
    @GET
    @Path("models")
    public Response getModelsLit(){
        List<String> modelLits = litService.getModelsLit();
        return Response
                .ok()
                .entity(modelLits)
                .build();
    }
    @GET
    @Path("types")
    public Response getTypesLit(){
        List<String> typesLit = litService.getTypesLit();
        return Response
                .ok()
                .entity(typesLit)
                .build();
    }
    @GET
    @Path("/{etat}")
    public Response getLitsByEtat(
            @PathParam(value = "etat") String etatLit){
        List<String> lits = new ArrayList<>();
        litService
                .findByEtat(etatLit)
                .forEach(elt -> lits.add(elt.toString()));
        return Response
                .ok()
                .entity(lits)
                .build();
    }
    @POST
    public Response postLitDescription(
            @QueryParam(value = "type") String type,
            @QueryParam(value = "model") String modelLit,
            @QueryParam(value = "fonctions") List<String> fonctions,
            @QueryParam(value = "dimensions")String dimensions,
            @QueryParam(value = "chargeMax") double chargeMax,
            @QueryParam(value = "garantie") int garantie,
            @QueryParam(value = "prix") double prix,
            @QueryParam(value = "frontColor") String frontColor,
            @QueryParam(value = "description") String description
    ){

        // Return id of LitDescription to create LitItems with it
        int idLitDescription = litService.addLitDescription(type, modelLit, dimensions, chargeMax, Period.of(garantie, 0, 0), prix, fonctions, frontColor, description);
            return Response
                    .ok()
                    .entity(idLitDescription)
                    .build();
        }
    @POST
    @Path("/items")
    public Response PostLitItem(
            @QueryParam(value = "quantity") int quantity,
            @QueryParam(value = "idLitDescription") int idLitDescription
        ){
        litService.addLits(quantity, idLitDescription);
        return Response
                .ok()
                .build();
        }

    // Admin

    @DELETE
    @Path("/{id}")
    public Response deleteLit(
            @PathParam(value = "id") int id){
        litService.deleteLit(id);
        return Response
                .ok()
                .build();
    }




    // Rachid

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    @RolesAllowed({"admin","normal"})

//    @PermitAll
    public Response getLits(
            @QueryParam(value = "nomDepartement") String nomDepartement
    ){
        List<LitItem> lits = new ArrayList<>();
//        return litService.getLits(nomDepartement);

        litService
                .getLits(nomDepartement)
                .forEach(elt -> lits.add(elt));

        JSONArray jlist=new JSONArray();
        for (LitItem lit:lits){
            JSONObject js=new JSONObject();
            js.put("idlit",lit.getId());
            js.put("Occupation",lit.getOccupied());
            js.put("codelit",lit.getCode());
            jlist.put(js);
        }
        return Response
                .ok()
                .entity(jlist.toString())
                .build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("litinespace")
//    @RolesAllowed("admin")
    @PermitAll
    public Response getLitsEspace() {
        HashMap<String, List<String>> mapchambr = new HashMap<>();

        HashMap<Espace,List<LitItem>> hashlitespae = litService.getLitsEspace("Cardiologie");

        for (Espace lst : hashlitespae.keySet()) {
            List<String> lstLits = new ArrayList<>();
            for (LitItem lit : hashlitespae.get(lst)) {
                lstLits.add(lit.toString());
            }
            mapchambr.put(lst.toString(), lstLits);
        }
        System.out.println("@@@@@@@@@@@@@@@@@"+hashlitespae.get(0));
        return Response.ok().entity(mapchambr).build();
    }

    @GET
    @Path("litdisponible")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","normal"})
    public Response getAllLitDisponible(
            @QueryParam(value = "nomDepartement") String nomDepartement,
            @QueryParam(value = "espaceType") String espaceType
    ){
        List<String> lits = new ArrayList<>();
        litService
                .Chercher_lit_disponbile_a_Reserver(nomDepartement,espaceType)
                .forEach(elt -> lits.add("{"+"id :"+elt.getId()+ ", Espace : "+elt.getEspace().getId()+"}"));
        return Response
                .ok()
                .entity(lits)
                .build();
    }
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("allreservation")
    @RolesAllowed({"admin","normal"})
    public Response getAllReservation(){
        List<String> lits = new ArrayList<>();

        litService
                .getReservationHistorique().forEach(elt -> lits.add(elt.toString()));
        return Response
                .ok()
                .entity(lits)
                .build();

    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed({"admin","normal"})
    public Response OccuperLit(
            @QueryParam(value = "id") int lit_id

    ){

        litService.reserverLit(lit_id);
        return Response
                .ok()
                .entity("rachid")
                .build();
    }

    ///                 <rachid> occuper ou liberer un lit
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("occuperlit")
    @RolesAllowed({"admin","normal"})

    public Response postOccuper_lit(
            @QueryParam(value="id") int id
    ){

        litService.occuperLit(id);

        return Response
                .ok()
                .entity("rachid")
                .build();
    }
}
