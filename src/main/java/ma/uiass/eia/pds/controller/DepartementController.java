package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.DepartementService;
import ma.uiass.eia.pds.metier.DepartementServiceImpl;
import ma.uiass.eia.pds.metier.LitManager;
import ma.uiass.eia.pds.metier.LitManagerImpl;

import java.util.ArrayList;
import java.util.List;

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

//    @GET
//    @Path("/{nomDepartement}/lits")
//    public Response getAllLitDepartement(
//            @PathParam(value = "nomDepartement") String nomDepartement) {
//        List<String> lits = new ArrayList<>();
//        litManager
//                .getAllLitStock()
//                .forEach(elt -> lits.add(elt.toString()));
//        return Response
//                .ok()
//                .entity(lits)
//                .build();
//    }

    ;

    @GET
    @Path("{nomDepartement}/{typeEspace}/lits")
    public Response getAllLitEspace(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace) {
        List<String> lstEspaces;
        switch (typeEspace) {
            case "Salle":
                lstEspaces = litManager.getAllLitSalle(nomDepartement);
                break;
            case "Chambre":
                lstEspaces = litManager.getAllLitChambre(nomDepartement);
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
    @GET
    @Path("{nomDepartement}/{typeEspace}/lits/{occupied}")
    public Response getAllLitEspaceEtat(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace,
            @PathParam(value = "occupied") Boolean occupied) {
        List<String> lstEspaces;
        switch (typeEspace) {

            case "Salle":
                if (!occupied) {
                    lstEspaces = litManager.getAllDisponibleLitSalle(nomDepartement);
                }
                else {
                    lstEspaces = litManager.getAllOccupeLitSalle(nomDepartement);
                }
                break;

            case "Chambre":
                if (!occupied) {
                    lstEspaces = litManager.getAllDisponibleLitChambre(nomDepartement);
                }
                else {
                    lstEspaces = litManager.getAllOccupeLitChambre(nomDepartement);
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
}
