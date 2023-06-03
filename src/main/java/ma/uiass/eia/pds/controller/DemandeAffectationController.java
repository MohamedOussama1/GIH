package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.DemandeAffectationService;
import ma.uiass.eia.pds.metier.DemandeAffectationServiceImpl;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.demande.DemandeAffectation;
import ma.uiass.eia.pds.model.demande.EtatDemande;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@Path("/demandeaffectation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DemandeAffectationController {
    DemandeAffectationService DAS = new DemandeAffectationServiceImpl();

    @GET
    public Response getAllDemandes(){
        List<DemandeAffectation> demandes = DAS.getAllDemandes();
        JSONArray jsonArray = new JSONArray();
        for (DemandeAffectation demande : demandes) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", demande.getId());
            jsonObject.put("etatDemande", demande.getEtatDemande().toString());
            jsonObject.put("date_debut", demande.getDate_debut().toString());
            jsonObject.put("date_fin", demande.getDate_fin().toString());
            jsonObject.put("qte", demande.getQte());
            jsonObject.put("typeLit", demande.getTypeLit().toString());
            jsonObject.put("modelLit", demande.getModelLit().toString());
            jsonObject.put("departement", demande.getDepartement().toString());
            jsonArray.put(jsonObject);
        }

        return Response.ok(jsonArray.toString(), MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("/demandesDepartement")
    public Response getDemandesByDepartement(
            @QueryParam(value = "nomDepartement") String nomDepartement){
        List<DemandeAffectation> demandes = DAS.getDemandesByDepartement(nomDepartement);
        JSONArray jsonArray = new JSONArray();
        for (DemandeAffectation demande : demandes) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", demande.getId());
            jsonObject.put("etatDemande", demande.getEtatDemande().toString());
            jsonObject.put("date_debut", demande.getDate_debut().toString());
            jsonObject.put("date_fin", demande.getDate_fin().toString());
            jsonObject.put("qte", demande.getQte());
            jsonObject.put("typeLit", demande.getTypeLit().toString());
            jsonObject.put("modelLit", demande.getModelLit().toString());
            jsonObject.put("departement", demande.getDepartement().toString());
            jsonArray.put(jsonObject);
        }

        return Response.ok(jsonArray.toString(), MediaType.APPLICATION_JSON).build();
    }




    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response postDemandeAffectation(
            @QueryParam("typeLit") String typeLit,
            @QueryParam("modelLit") String modelLit,
            @QueryParam("Departement") String nomDepartement,
            @QueryParam("qte") int qte
    ) {
        TypeLit parsedTypeLit = TypeLit.fromString(typeLit);
        ModelLit parsedModelLit = ModelLit.valueOf(modelLit);

        DAS.ajouterDemandeAffectation(
                parsedTypeLit,
                parsedModelLit,
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
        DAS.updateEtatDemande(id, EtatDemande.fromString(etatDemande));
        return Response
                .ok()
                .build();
    }


}

