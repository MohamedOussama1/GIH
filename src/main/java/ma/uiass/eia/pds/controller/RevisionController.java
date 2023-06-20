package ma.uiass.eia.pds.controller;

import jakarta.annotation.security.PermitAll;
import ma.uiass.eia.pds.metier.RevisionService;
import ma.uiass.eia.pds.metier.RevisionServiceImpl;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.model.Ambulance.Ambulance;
import ma.uiass.eia.pds.model.Ambulance.EtatAmbulance;
import ma.uiass.eia.pds.persistance.AmbulanceRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

@Path("revision")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RevisionController {
    RevisionService revisionService = new RevisionServiceImpl();

    @POST
    public Response createRevision(
            @QueryParam(value = "immatriculation") String immatriculation,
            @QueryParam(value = "dateDebut") String dateDebut,
            @QueryParam(value = "typeRevision") String typeRevision) {
        int id = revisionService.createRevision(immatriculation, LocalDate.parse(dateDebut), typeRevision);
        return Response
                .ok()
                .entity(id)
                .build();
    }
    @GET
    public Response getRevisionsByAmbulance(
            @QueryParam(value = "immatriculation") String immatriculation){
        List<String> revisions = revisionService.getRevisionsByAmbulance(immatriculation);
        System.out.println(revisions);
        return Response
                .ok()
                .entity(revisions)
                .build();
    }
    @PUT
    @Path("{endDate}")
    public Response endCurrentRevision(
            @QueryParam(value = "immatriculation") String immatriculation,
            @PathParam(value = "endDate") String endDate){
        revisionService.endCurrentRevision(immatriculation, LocalDate.parse(endDate));
        return Response
                .ok()
                .build();
    }
    @PUT
    @Path("update/{id}")
    public Response upDateRevision(
            @PathParam(value = "id") int revisionId,
            @QueryParam(value = "typeRevision") String typeRevision,
            @QueryParam(value = "dateDebut") String dateDebut,
            @QueryParam(value = "dateSortie") String dateSortie,
            @QueryParam(value = "ancienKm") int ancienKm,
            @QueryParam(value = "nouvelKm") int nouvelKm,
            @QueryParam(value = "description") String description
    ){
        revisionService.updateRevision(revisionId, typeRevision, LocalDate.parse(dateDebut), LocalDate.parse(dateSortie), ancienKm, nouvelKm, description);
        return Response
                .ok()
                .build();
    }
    @DELETE
    public Response deleteRevision(
            @QueryParam(value = "revisionId") int revisionId
    ){
        revisionService.deleteRevision(revisionId);
        return Response
                .ok()
                .build();
    }

    @GET
    @Path("type")
    public Response getTypesRevision(){
        return Response
                .ok()
                .entity(revisionService.getTypesRevision())
                .build();
    }
    @GET
    @Path("{immatriculation}/isInRevision")
    public Response isInRevision(
            @PathParam(value = "immatriculation") String immatriculation
    ){
        int isIn = revisionService.isInRevision(immatriculation);
        return Response
                .ok()
                .entity(isIn)
                .build();
    }
    @PUT
    @Path("update")
    public Response update_etat_ambu(
            @QueryParam("id_ambulance") int id_dmItem,
            @QueryParam("etat_ambulance") String etat_ambulance
    ) {

        AmbulanceRepositoryImpl dd=new AmbulanceRepositoryImpl();

        dd.update_ambulance_etat(id_dmItem,etat_ambulance);

        return Response
                .status(Response.Status.CREATED)
                .entity("update state with successfull")
                .build();
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("predict_y")
//    @RolesAllowed("admin")
    @PermitAll
    public Response getPrediction(@QueryParam(value = "immatriculation") String immatriculation)  {

        AmbulanceRepositoryImpl dd=new AmbulanceRepositoryImpl();

        Ambulance ambu=dd.getAmbulanceByImmatriculation(immatriculation);


        EtatAmbulance etat=ambu.getEtat_object();

        etat.setAmbulance(ambu);

        double y=etat.predict_Y();
//
        System.out.println(y);
        return Response
                .status(Response.Status.CREATED)
                .entity(y)
                .build();

    }
}
