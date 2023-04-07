package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.model.Lit.Dimensions;
import ma.uiass.eia.pds.model.Lit.enums.EtatLit;
import ma.uiass.eia.pds.metier.LitService;
import ma.uiass.eia.pds.metier.LitServiceImpl;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.reservation.Reservation;
import org.json.JSONObject;

import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("lits")
public class LitController {

    private LitService litService = new LitServiceImpl();
    @GET
    @Path("stock")
    @Produces(MediaType.APPLICATION_JSON)
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
    @Consumes(MediaType.APPLICATION_JSON)
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
        // Extract longeur, largeur, hauteur from String dimensions as List<Double>
        List<Double> dimensionsValues = Arrays.stream(dimensions.split("x")).map(Double::valueOf).collect(Collectors.toList());
        Dimensions litDimension = new Dimensions(dimensionsValues.get(0), dimensionsValues.get(1), dimensionsValues.get(2));

        // Convert List<String> fonctions to Set<FonctionLit> fonctions
        Set<FonctionLit> fonctionsLit = new HashSet<>();
        fonctions.forEach(elt -> fonctionsLit.add(FonctionLit.valueOf(elt)));
        // Return id of LitDescription to create LitItems with it
        int idLitDescription = litService.addLitDescription(TypeLit.valueOf(type), ModelLit.valueOf(modelLit), litDimension, chargeMax, Period.of(garantie, 0, 0), prix, fonctionsLit, frontColor, description);
            return Response
                    .ok()
                    .entity(idLitDescription)
                    .build();
        }
    @Consumes(MediaType.APPLICATION_JSON)
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

    // Gerer Reservations
    @POST
    @Path("reservation")
    public Response OccuperLit(
            Reservation reservation
    ){
        litService.reserverLit(reservation.getDateDebut(), reservation.getDateFin(), reservation.getDateReservation(), reservation.getLit().getId(), reservation.getPatient().getId());
        return Response
                .ok()
                .entity(reservation.getDateDebut())
                .build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("reservation")
    public Response getReservations(
            @QueryParam(value = "id") int idLit
    ){
        List<String> reservations = new ArrayList<>();
        litService
                .findReservations(idLit)
                .forEach(elt -> reservations.add(elt.toString()));
        return Response
                .ok()
                .entity(reservations)
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
}
