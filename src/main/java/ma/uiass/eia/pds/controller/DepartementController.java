package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.DepartementService;
import ma.uiass.eia.pds.metier.DepartementServiceImpl;
import ma.uiass.eia.pds.metier.LitManager;
import ma.uiass.eia.pds.metier.LitManagerImpl;
import ma.uiass.eia.pds.model.Lit.LitItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;
import org.json.JSONObject;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/departement")
public class DepartementController {
    DepartementService departementService = new DepartementServiceImpl();
    LitManager litManager = new LitManagerImpl();

    @POST
    @Path("{nomDepartement}")
    public Response addDepartement(
            @PathParam(value = "nomDepartement") String nomDepartement) {
        departementService.addDepartement(nomDepartement);
        return Response
                .ok()
                .build();
    }

    @GET
    public Response getAllDepartement() {
        List<String> departements = new ArrayList<>();
        departementService
                .getAllDepartement()
                .forEach(elt -> departements.add(elt.getNomDepartement()));
        System.out.println(departements);
        return Response
                .ok()
                .entity(departements)
                .build();
    }
    @GET
    @Path("stock")
    public Response getLitsStock(
            @QueryParam(value = "nomDepartement") String nomDepartement
    ){
        List<String> lits = new ArrayList<>();
        departementService
                .getLitsStock(nomDepartement)
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
    @Path("/{nomDepartement}/espaces/{typeEspace}")
    public Response getCodesEspacesDepartement(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace
    ) {
        return Response
                .ok()
                .entity(departementService
                        .getAllCodeEspace(nomDepartement, typeEspace)
                )
                .build();
    }

    @GET
    @Path("/{nomDepartement}/{codeEspace}/codesLits")
    public Response getCodesLitsEspace(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "codeEspace") int numEspace
    ) {
        return Response
                .ok()
                .entity(departementService
                        .getAllCodeLit(nomDepartement, numEspace)
                )
                .build();
    }

    @GET
    @Path("/{nomDepartement}/lits")
    public Response getAllLitDepartement(
            @PathParam(value = "nomDepartement") String nomDepartement) {
        List<String> lits = new ArrayList<>();
        litManager
                .getAllLitStock()
                .forEach(elt -> lits.add(elt.toString()));
        return Response
                .ok()
                .entity(lits)
                .build();
    }

    ;

    @GET
    @Path("{nomDepartement}/{typeEspace}/lits")
    public Response getAllLitEspace(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace) {
        List<String> lstEspaces = new ArrayList<>();
        switch (typeEspace) {
            case "Salle":
                populateJsonListSalle(lstEspaces, litManager.getAllLitSalle(nomDepartement));
                break;
            case "Chambre":
                populateJsonListChambre(lstEspaces, litManager.getAllLitChambre(nomDepartement));
                break;
            default:
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .build();
        }
        return Response
                .ok()
                .entity(lstEspaces)
                .build();
    }

    ;

    @GET
    @Path("{nomDepartement}/{typeEspace}/lits/{occupied}")
    public Response getAllLitEspaceEtat(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace,
            @PathParam(value = "occupied") Boolean occupied) {
        List<String> lstEspaces = new ArrayList<>();
        switch (typeEspace) {

            case "Salle":
                if (!occupied) {
                    populateJsonListSalle(lstEspaces, litManager.getAllDisponibleLitSalle(nomDepartement));
                } else {
                    populateJsonListSalle(lstEspaces, litManager.getAllOccupeLitSalle(nomDepartement));
                }
                break;

            case "Chambre":
                if (!occupied) {
                    populateJsonListChambre(lstEspaces, litManager.getAllDisponibleLitChambre(nomDepartement));
                } else {
                    populateJsonListChambre(lstEspaces, litManager.getAllOccupeLitChambre(nomDepartement));
                }
                break;

            default:
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .build();
        }
        return Response
                .ok()
                .entity(lstEspaces)
                .build();
    }

    // Localisation
    @PUT
    @Path("{nomDepartement}")
    public Response deplacerLit(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @QueryParam(value = "idLit") int idLit,
            @QueryParam(value = "typeEspace") String typeEspace,
            @QueryParam(value = "numEspace") int numEspace
    ) {
        litManager.deplacerLit(nomDepartement, typeEspace, numEspace, idLit);
        return Response
                .ok()
                .build();
    }

    public void populateJsonListSalle(List<String> jsonList, Map<Salle, List<LitItem>> espaceBedMap) {
        espaceBedMap.forEach((espace, litLst) -> {
            JSONObject espaceJson = new JSONObject();
            espaceJson.put("salle", new JSONObject(espace));
            espaceJson.put("litLst", litLst);
            jsonList.add(espaceJson.toString());
        });
    }

    public void populateJsonListChambre(List<String> jsonList, Map<Chambre, List<LitItem>> espaceBedMap) {
        espaceBedMap.forEach((espace, litLst) -> {
            JSONObject espaceJson = new JSONObject();
            espaceJson.put("chambre", new JSONObject(espace));
            espaceJson.put("litLst", litLst);
            jsonList.add(espaceJson.toString());
        });
    }
}
