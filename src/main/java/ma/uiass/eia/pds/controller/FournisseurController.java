package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.FournisseurService;
import ma.uiass.eia.pds.metier.FournisseurServiceImpl;
import ma.uiass.eia.pds.model.fournisseur.Fournisseur;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)

@Path("/fournisseur")
public class FournisseurController {
    FournisseurService fournisseurService = new FournisseurServiceImpl();

    @GET
    public Response getAll() {
        List<String> fournisseurs = new ArrayList<>();
        fournisseurService
                .getAll()
                .forEach(elt -> fournisseurs.add(elt.getNom()));
        return Response
                .ok()
                .entity(fournisseurs)
                .build();
    }


    @GET
    @Path("/listfournisseur")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFournisseurs() {
        List<Fournisseur> fournisseurList = fournisseurService.getAll();
        JSONArray jsonArray = new JSONArray();
        for (Fournisseur elt : fournisseurList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", elt.getId());
            jsonObject.put("nom", elt.getNom());
            jsonObject.put("email", elt.getEmail());
            jsonObject.put("ville", elt.getVille());
            jsonObject.put("codeape", elt.getCodeAPE());
            jsonObject.put("formejuridique", elt.getFormeJuridique());
            jsonObject.put("num_siren", elt.getNumSiren());
            jsonObject.put("tel", elt.getTel());
            jsonArray.put(jsonObject);
        }

        return Response.ok(jsonArray.toString(), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/postFournisseur")
    public Response saveFournisseur(
            @QueryParam(value = "fournisseur_nom") String nom,
            @QueryParam(value = "fournisseur_ville") String ville,
            @QueryParam(value = "fournisseur_email") String email,
            @QueryParam(value = "fournisseur_codeape") String codeAPE,
            @QueryParam(value = "fournisseur_formejuridique") String formeJuridique,
            @QueryParam(value = "fournisseur_numsiren") String numSiren,
            @QueryParam(value = "fournisseur_tel") String tel
    ) {
        fournisseurService.saveFournisseur(nom, ville, email, codeAPE, formeJuridique, numSiren, tel);
        return Response.ok().build();
    }


    @DELETE
    @Path("/deleteFournisseur")
    public Response deleteFournisseur(
            @QueryParam(value = "fournisseur_id") int id
    ) {
        int deleted = fournisseurService.deleteFournisseur(id);
        if (deleted == 1) {
            return Response.ok().build();
        }else {
            return Response.status(1000).build();
        }
    }

    @PUT
    @Path("/updateFournisseur")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyFournisseur(
            @QueryParam(value = "fournisseur_id") int id,
            @QueryParam(value = "fournisseur_nom") String nom,
            @QueryParam(value = "fournisseur_ville") String ville,
            @QueryParam(value = "fournisseur_email") String email,
            @QueryParam(value = "fournisseur_codeape") String codeAPE,
            @QueryParam(value = "fournisseur_formejuridique") String formeJuridique,
            @QueryParam(value = "fournisseur_numsiren") String numSiren,
            @QueryParam(value = "fournisseur_tel") String tel
    ){
        fournisseurService.modifyFournisseur(id,nom,ville,email,codeAPE,formeJuridique,numSiren,tel);
        return Response.ok().build();
    }
}
