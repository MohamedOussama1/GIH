package ma.uiass.eia.pds.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.AmbulanceService;
import ma.uiass.eia.pds.metier.AmbulanceServiceImpl;
import ma.uiass.eia.pds.model.Ambulance.Ambulance;
import ma.uiass.eia.pds.model.Ambulance.AmbulanceType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Path("/ambulances")
public class AmbulanceController {

    AmbulanceService ambulanceService = new AmbulanceServiceImpl();




    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAmbulances() {
        List<Ambulance> ambulances = ambulanceService.getAllAmbulances();
        JSONArray jsonArray = new JSONArray();
        for (Ambulance ambulance : ambulances) {
            System.out.println(ambulance.getAmbulanceType().name());
            System.out.println(ambulance.getModel());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", ambulance.getId());
            jsonObject.put("immatriculation", ambulance.getImmatriculation());
            jsonObject.put("km", ambulance.getKm());
            jsonObject.put("date_mise_service", ambulance.getDate_mise_service());
            jsonObject.put("etatAmbulance", ambulance.getEtatAmbulance().toString());
            jsonObject.put("typeAmbulance", ambulance.getAmbulanceType().name());
            jsonObject.put("model",ambulance.getModel());

            jsonArray.put(jsonObject);
        }
        return Response.ok(jsonArray.toString(), MediaType.APPLICATION_JSON).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postAmbulance(
            @QueryParam("immatriculation") String immatriculation,
            @QueryParam("date_mise_en_service") String date_mise_en_service,
            @QueryParam("km") int km,
            @QueryParam("type_ambulance") String typeAmbulance,
            @QueryParam("model") String model)
            throws ParseException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(date_mise_en_service, formatter);
        System.out.println("-----------------------------------");
        System.out.println(typeAmbulance);
        System.out.println("-----------------------------------");
        System.out.println(AmbulanceType.valueOf(typeAmbulance));
        System.out.println("-----------------------------------");
        ambulanceService.createAmbulance(immatriculation,km,date, AmbulanceType.valueOf(typeAmbulance),model);
        System.out.println("Hello");
        // Return a response with a success message
        String successMessage = "Ambulance ajoutée avec succès.";
        return Response.status(Response.Status.OK).entity(successMessage).build();
    }



    @GET
    @Path("etatambulance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtatAmbulance() {
        List<String> etatsAmbulance = new ArrayList<>();
        etatsAmbulance.add("F");
        etatsAmbulance.add("NFCD");
        etatsAmbulance.add("NFLD");
        return Response
                .ok()
                .entity(etatsAmbulance)
                .build();
    }
    @PUT
    @Path("/updateambulance")
    public Response updateAmbulance(
            @QueryParam("id") int id,
            @QueryParam("immatriculation") String immatriculation,
            @QueryParam("date_mise_en_service") String date_mise_en_service,
            @QueryParam("km") int km,
            @QueryParam("type_ambulance") String typeAmbulance,
            @QueryParam("model") String model
    ) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(date_mise_en_service, formatter);
        ambulanceService.updateAmbulance(id,immatriculation,date,km, AmbulanceType.valueOf(typeAmbulance),model);
        return Response
                .ok()
                .build();

    }
    @DELETE
    @Path("/deleteambulance")
    public Response deleteAmbulance(
            @QueryParam("id") int id){
        ambulanceService.deleteAmbulance(id);
        return Response
                .ok()
                .build();
    }

//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("postetatambulance")
//    @POST
//    public Response postEtatAmbulance(
//            @QueryParam("etatAmbulance") String etatAmbulance
//    ) {
//        ambulanceService.createEtatAmbulance(etatAmbulance);
//
//        return Response.ok().build();
//    }

}
