package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
                        .forEach(elt -> {
                            lits.add(elt.toString());
                        });
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
    @Path("{nomDepartement}/{typeEspace}/lits/{etatLit}")
    public Response getAllLitEspaceEtat(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace,
            @PathParam(value = "etatLit") String etatLit) {
        List<String> lits = new ArrayList<>();
        switch (typeEspace) {
            case "Salle":
                switch (etatLit) {
                    case "Disponible":
                        litManager
                                .getAllDisponibleLitSalle(nomDepartement)
                                .forEach(elt -> lits.add(elt.toString()));
                        return Response
                                .ok()
                                .entity(lits)
                                .build();
                    case "Occupé":
                        litManager
                                .getAllOccupeLitSalle(nomDepartement)
                                .forEach(elt -> lits.add(elt.toString()));
                        return Response
                                .ok()
                                .entity(lits)
                                .build();
                    default:
                        return Response
                                .status(Response.Status.NOT_FOUND)
                                .build();
                }
            case "Chambre":
                switch (etatLit) {
                    case "Disponible":
                        litManager
                                .getAllDisponibleLitChambre(nomDepartement)
                                .forEach(elt -> lits.add(elt.toString()));
                        return Response
                                .ok()
                                .entity(lits)
                                .build();
                    case "Occupé":
                        litManager
                                .getAllOccupeLitChambre(nomDepartement)
                                .forEach(elt -> lits.add(elt.toString()));
                        return Response
                                .ok()
                                .entity(lits)
                                .build();
                    default:
                        return Response
                                .status(Response.Status.NOT_FOUND)
                                .build();
                }
            default:
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .build();
        }
    }
}
