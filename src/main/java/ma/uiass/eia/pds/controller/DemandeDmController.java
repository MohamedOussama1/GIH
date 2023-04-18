package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.DemandeDmService;
import ma.uiass.eia.pds.metier.DemandeDmServiceImpl;
import ma.uiass.eia.pds.model.demande.DemandeDm;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@Path("demandeDm")
public class DemandeDmController {

    DemandeDmService demandeDmService = new DemandeDmServiceImpl();

    @GET
    public Response getAllDemandes(){
        List<DemandeDm> demandes = demandeDmService.getAllDemandes();
        JSONArray jsonArray = new JSONArray();
        for (DemandeDm demande : demandes) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", demande.getId());
            jsonObject.put("etatDemande", demande.getEtatDemande().toString());
            jsonObject.put("date_debut", demande.getDate_debut().toString());
            jsonObject.put("date_fin", demande.getDate_fin().toString());
            jsonObject.put("qte", demande.getQte());
            jsonObject.put("typeDm", demande.getTypeDM().getNomType().toString());
            jsonObject.put("nomDm", demande.getNomDm().toString());
            jsonObject.put("departement", demande.getDepartement().toString());
            jsonArray.put(jsonObject);
        }

        return Response.ok(jsonArray.toString(), MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("/demandesDepartement")
    public Response getDemandesByDepartement(
            @QueryParam(value = "nomDepartement") String nomDepartement){
        List<DemandeDm> demandes = demandeDmService.getDemandesByDepartement(nomDepartement);
        JSONArray jsonArray = new JSONArray();
        for (DemandeDm demande : demandes) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", demande.getId());
            jsonObject.put("etatDemande", demande.getEtatDemande().toString());
            jsonObject.put("date_debut", demande.getDate_debut().toString());
            jsonObject.put("date_fin", demande.getDate_fin().toString());
            jsonObject.put("qte", demande.getQte());
            jsonObject.put("typeDm", demande.getTypeDM().getNomType().toString());
            jsonObject.put("nomDm", demande.getNomDm().toString());
            jsonObject.put("departement", demande.getDepartement().toString());
            jsonArray.put(jsonObject);
        }

        return Response.ok(jsonArray.toString(), MediaType.APPLICATION_JSON).build();
    }




    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response postDemandeDm(
            @QueryParam("typeDm") String typeDm,
            @QueryParam("nomDm") String nomDm,
            @QueryParam("Departement") String nomDepartement,
            @QueryParam("qte") int qte
    ) {
        demandeDmService.ajouterDemandeDm(
                typeDm,
                nomDm,
                nomDepartement,
                qte
        );

        return Response.ok().build();
    }

    @PUT
    @Path("/{id}/{etat}")
    public Response modifyLit(
            @PathParam(value = "id") int id,
            @PathParam(value = "etat") String etatDemande){
        demandeDmService.updateEtatDemande(id, etatDemande);
        return Response
                .ok()
                .build();
    }

}
