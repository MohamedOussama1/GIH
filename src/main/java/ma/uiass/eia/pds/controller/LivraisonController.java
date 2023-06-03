package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.DmManager;
import ma.uiass.eia.pds.metier.DmManagerImpl;
import ma.uiass.eia.pds.metier.LivraisonService;
import ma.uiass.eia.pds.metier.LivraisonServiceImpl;
import ma.uiass.eia.pds.model.livraison.LivraisonDM;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/livraisonDM")
public class LivraisonController {
    LivraisonService livraisonService = new LivraisonServiceImpl();
    DmManager dmm = new DmManagerImpl();


    /*@POST
    @Path("/postDetailLivraison")
    public Response saveDetailLivraison1(
            @QueryParam("qte") int qte,
            @QueryParam("dm") String dm
    ) {
        livraisonService.saveDetailLivraison(qte, dm);
        return Response
                .status(Response.Status.CREATED)
                .entity("DL created successfully")
                .build();
    }*/

//    @POST
//    @Path("/detailLivraison")
//    public Response saveDetailLivraison(
//            @QueryParam(value = "qte") int qte,
//            @QueryParam(value = "dm") String dm
//    ){
//        livraisonService.saveDetailLivraison(qte,dm);
//        return Response.ok()
//                .entity("Detail livraison created")
//                .build();
//    }

    @POST
    @Path("/postLivraison")
    public Response saveLivraison(
            @QueryParam("detailsFournisseur")String detaillivraison
            ){
        System.out.println(detaillivraison);
        JSONObject jsonObject = new JSONObject(detaillivraison);
        String fournisseur = jsonObject.getString("fournisseur");
        JSONArray jsonArray = new JSONArray(jsonObject.getString("detailsLst"));
//        JSONObject obj = new JSONObject(jsonArray.get(0).toString());
//        System.out.println(obj.get("dm"));

        livraisonService.saveLivraison(fournisseur,jsonArray);
        return Response
                .ok()
                .entity("Livraison created successfully")
                .build();
    }


    @GET
    @Path("livraisonlist")
    public Response getAllLivraison(){
        List<LivraisonDM> livraisons = livraisonService.getAllLivraison();
        JSONArray jsonArray = new JSONArray();
        for (LivraisonDM elt : livraisons) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", elt.getId());
            jsonObject.put("fournisseur", elt.getFournisseur().getNom().toString());
            jsonObject.put("date", elt.getDateLivraison().toString());
            jsonObject.put("detailsLivraison", elt.getDetailLivraisons());
            jsonArray.put(jsonObject);
        }

        return Response.ok(jsonArray.toString(), MediaType.APPLICATION_JSON).build();
    }


}

