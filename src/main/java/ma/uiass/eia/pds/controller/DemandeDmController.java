package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.DemandeDmService;
import ma.uiass.eia.pds.metier.DemandeDmServiceImpl;

import java.util.List;

@Path("demandeDm")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DemandeDmController {

    DemandeDmService demandeDmService = new DemandeDmServiceImpl();

    @GET
    public Response getAllDemandes(){
        List<String> demandes = demandeDmService.getAllDemandes();
        return Response
                .ok()
                .entity(demandes)
                .build();
    }
    @GET
    @Path("/demandesDepartement")
    public Response getDemandesByDepartement(
            @QueryParam(value = "nomDepartement") String nomDepartement){
        List<String> demandes = demandeDmService.getDemandesByDepartement(nomDepartement);
        return Response
                .ok()
                .entity(demandes)
                .build();
    }




    @POST
    public Response postDemandeDm(
            @QueryParam("nomDepartement") String nomDepartement
    ) {
        int idDemandeDm = demandeDmService.ajouterDemandeDm(nomDepartement);

        return Response
                .ok()
                .entity(idDemandeDm)
                .build();
    }
    @POST
    @Path("detailDemande")
    public Response postDetailDemandeDm(
            @QueryParam("nomDm") String nomDm,
            @QueryParam("qte") int qte,
            @QueryParam("idDemandeDm") int idDemandeDm
    ){
        demandeDmService.ajouterDetailDemandeDm(nomDm, qte, idDemandeDm);
        return Response
                .ok()
                .build();
    }

    @PUT
    @Path("/{id}/{etat}")
    public Response modifyEtatDemande(
            @PathParam(value = "id") int id,
            @PathParam(value = "etat") String etatDemande){
        demandeDmService.updateEtatDemande(id, etatDemande);
        return Response
                .ok()
                .build();
    }
    @PUT
    @Path("detailDemande")
    public Response modifyEtatDetail(
            @QueryParam(value = "idDetail") int idDetail,
            @QueryParam(value = "etatDetail") String etatDetail
    ){
        demandeDmService.updateEtatDetail(idDetail, etatDetail);
        return Response
                .ok()
                .build();
    }

}
