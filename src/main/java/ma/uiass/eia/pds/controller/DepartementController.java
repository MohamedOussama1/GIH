package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/departement")
public class DepartementController {
    @GET
    public Response getAllDepartement(){
        Response response = null;
        return response;
    };
    @GET
    @Path("/{nomDepartement}/lits")
    public Response getAllLitDepartement(
            @PathParam(value = "nomDepartement") String nomDepartement){
        Response response = null;
        return response;
    };
    @GET
    @Path("{nomDepartement}/{typeEspace}/lits")
    public Response getAllLitEspace(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace){
        Response response = null;
        return response;
    };
    @GET
    @Path("{nomDepartement}/{typeEspace}/lits/{etatLit}")
    public Response getAllLitEspaceEtat(
            @PathParam(value = "nomDepartement") String nomDepartement,
            @PathParam(value = "typeEspace") String typeEspace,
            @PathParam(value = "etatLit") String etatLit){
        Response response = null;
        return response;
    };
}
