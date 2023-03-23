package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.DepartementService;
import ma.uiass.eia.pds.metier.DepartementServiceImpl;
import ma.uiass.eia.pds.metier.LitManager;
import ma.uiass.eia.pds.metier.LitManagerImpl;
import ma.uiass.eia.pds.model.departement.Departement;

import java.util.ArrayList;
import java.util.List;

@Path("/departement")
public class DepartementController {
    DepartementService departementService = new DepartementServiceImpl();
    LitManager litManager = new LitManagerImpl();
    @POST
    @Path("{nomDepartement}")
    public Response addDepartement(
            @PathParam(value = "nomDepartement") String nomDepartement){
        departementService.addDepartement(nomDepartement);
        return Response
                .ok()
                .build();
    }
    @GET
    public Response getAllDepartement(){
        List<String> departements = new ArrayList<>();
        departementService
                .getAllDepartement()
                .forEach(elt -> departements.add(elt.getNomDepartement()));
        return Response
                .ok()
                .entity(departements)
                .build();
    };
    @GET
    @Path("/{nomDepartement}/espaces/{typeEspace}")
    public Response getCodesEspacesDepartement(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace
    ){
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
        ){
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
            @PathParam(value = "nomDepartement") String nomDepartement){
        List<String> lits = new ArrayList<>();
        litManager
                .getAllLit(nomDepartement)
                .forEach(elt -> lits.add(elt.toString()));
        return Response
                .ok()
                .entity(lits)
                .build();
    };
    @GET
    @Path("{nomDepartement}/{typeEspace}/lits")
    public Response getAllLitEspace(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace){
        List<String> lits = new ArrayList<>();
        switch (typeEspace) {
            case "Salle":
                litManager
                        .getAllLitSalle(nomDepartement)
                        .forEach(elt -> lits.add(elt.toString()));
                break;
            case "Chambre":
                litManager
                        .getAllLitChambre(nomDepartement)
                        .forEach(elt -> lits.add(elt.toString()));
                break;
            default:
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .build();
        }
        return Response
                .ok()
                .entity(lits)
                .build();
    };
    @GET
    @Path("{nomDepartement}/{typeEspace}/lits/{occupied}")
    public Response getAllLitEspaceEtat(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace,
            @PathParam(value = "occupied")Boolean occupied){
        List<String> lits = new ArrayList<>();
        switch (typeEspace) {
            case "Salle":
                if (!occupied) {
                    litManager
                            .getAllDisponibleLitSalle(nomDepartement)
                            .forEach(elt -> lits.add(elt.toString()));
                    return Response
                            .ok()
                            .entity(lits)
                            .build();
                }else{
                        litManager
                                .getAllOccupeLitSalle(nomDepartement)
                                .forEach(elt -> lits.add(elt.toString()));
                        return Response
                                .ok()
                                .entity(lits)
                                .build();
                }
            case "Chambre":
                if (!occupied) {
                    litManager
                            .getAllDisponibleLitChambre(nomDepartement)
                            .forEach(elt -> lits.add(elt.toString()));
                    return Response
                            .ok()
                            .entity(lits)
                            .build();
                }else {
                    litManager
                                .getAllOccupeLitChambre(nomDepartement)
                                .forEach(elt -> lits.add(elt.toString()));
                        return Response
                                .ok()
                                .entity(lits)
                                .build();
                }
            default:
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .build();
        }
    }
    // Localisation
    @PUT
    @Path("{nomDepartement}")
    public Response deplacerLit(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @QueryParam(value = "idLit") int idLit,
            @QueryParam(value = "typeEspace") String typeEspace,
            @QueryParam(value = "numEspace") int numEspace
    ){
        litManager.deplacerLit(nomDepartement, typeEspace, numEspace, idLit);
        return Response
                .ok()
                .build();
    }
}
