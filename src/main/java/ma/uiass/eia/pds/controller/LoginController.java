package ma.uiass.eia.pds.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;


@Path("/login")
public class LoginController {

    @GET
    @Path("admin")
    @RolesAllowed("admin")
    public Response checkAdmin() {

        return Response
                .ok()
                .entity("Hi, admin")
                .build();
    }


    @GET
    @Path("normal")
    @RolesAllowed("normal")
    public Response checkNormalUser() {

        return Response
                .ok()
                .entity("Hi, normal")
                .build();
    }

    @GET
    @Path("all")
    @PermitAll
    public Response all() {

        return Response
                .ok()
                .entity("Hi, everyOne")
                .build();
    }


}
