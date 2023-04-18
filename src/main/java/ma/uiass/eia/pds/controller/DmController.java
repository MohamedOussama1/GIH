package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.DmManager;
import ma.uiass.eia.pds.metier.DmManagerImpl;
import ma.uiass.eia.pds.model.dm.TypeDM;

import java.util.ArrayList;
import java.util.List;

@Path("/dm")

public class DmController {

    DmManager DMM= new DmManagerImpl();

    @GET
    @Path("typedm")
    public Response getAllDepartement() {
        List<String> typedm = new ArrayList<>();
        DMM
                .getAllTypeDM()
                .forEach(elt -> typedm.add(elt.getNomType()));
        return Response
                .ok()
                .entity(typedm)
                .build();
    }



    @POST
    @Path("/postdm")
    public Response postDM(
            @QueryParam("nom") String nom,
            @QueryParam("qte") int qte,
            @QueryParam("typedm") String typedm
    ) {
        TypeDM TypeDM = DMM.getTypeDmByName(typedm);
        DMM.saveDM(nom, qte, TypeDM);
        return Response
                .status(Response.Status.CREATED)
                .entity("DM created successfully")
                .build();
    }

    //@Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/posttypedm")
    public Response postTypeDM(
            @QueryParam("nomtypedm") String nom
    ) {
        DMM.saveTypeDM(nom);
        return Response
                .ok()
                .build();
    }
    @GET
    @Path("/dmbyType")
    public Response getAllDM(
            @QueryParam("typeDM") String typeDM
    ) {
        List<String> DM = new ArrayList<>();
        DMM
                .getAllDMByType(typeDM)
                .forEach(elt -> DM.add(elt.getNom()));
        return Response
                .ok()
                .entity(DM)
                .build();
    }


}
