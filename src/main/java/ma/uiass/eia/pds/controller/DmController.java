package ma.uiass.eia.pds.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.uiass.eia.pds.metier.DmManager;
import ma.uiass.eia.pds.metier.DmManagerImpl;
import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.DMItem;
import ma.uiass.eia.pds.model.dm.TypeDM;
import ma.uiass.eia.pds.model.espace.Espace;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Path("/dm")

public class DmController {

    DmManager DMM= new DmManagerImpl();
    static String path="C:\\Users\\Fujitsu LifeBook u\\Downloads\\demoJakarta22\\src\\main\\java\\ma\\uiass\\eia\\pds\\controller";



    @POST
    @Path("postdm")
    public Response postDM(
            @QueryParam("nom") String nom,
            @QueryParam("qte") int qte,
            @QueryParam("typedm") String typedm,
            @QueryParam("image_path") String image_path
    ) {

        System.out.println(typedm);

        String[] part=typedm.split("\\|");
        System.out.println(part);
        String typedm2=part[0].trim();
        System.out.println(typedm2);
        String categorie=part[1].trim();
        System.out.println(categorie);
        TypeDM TypeDM = DMM.getTypeDmByName(typedm2,categorie);
        DMM.saveDM(nom, qte, TypeDM,image_path);
        return Response
                .status(Response.Status.CREATED)
                .entity("DM created successfully")
                .build();
    }



    @GET
    @Path("typedm")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlltypedm() {
        //  List<String> typedm = new ArrayList<>();

        JSONArray jlist=new JSONArray();
        if(DMM.getAllTypeDM() != null) {
            for (TypeDM lit : DMM.getAllTypeDM()) {
                JSONObject js = new JSONObject();
                js.put("id_dm", lit.getId());
                js.put("Nom", lit.getNomType());
                js.put("categorie",lit.getCategorie());
                jlist.put(js);
            }

        }
//        DMM.getAllTypeDM()
//                .forEach(elt -> typedm.add(elt.getNomType().toString()));
        return Response
                .ok()
                .entity(jlist.toString())
                .build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDMs() {
        List<DM> dms = DMM.getAllDM();
        JSONArray jsonArray = new JSONArray();
        for (DM dm : dms) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", dm.getId());
            jsonObject.put("nom", dm.getNom());
            jsonObject.put("quantite", dm.getQuantite());
            jsonObject.put("typeDM", dm.getTypeDM().getNomType());
            jsonArray.put(jsonObject);
        }

        return Response.ok(jsonArray.toString(), MediaType.APPLICATION_JSON).build();
    }


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
    @Path("/posttypedm")
    public Response postTypeDM(
            @QueryParam("nomtypedm") String nom,@QueryParam(value = "categorie") String categorie
    ) {
        DMM.saveTypeDM(nom,categorie);
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

    @DELETE
    @Path("/deletenomdm")
    public Response deleteDM(
            @QueryParam("nomdm") String nom){
        DMM.deleteDM(nom);
        return Response
                .ok()
                .build();
    }


    @PUT
    @Path("/updatenomdm")
    public Response updateNomDM(
            @QueryParam("oldnamedm") String oldnamedm,
            @QueryParam("newnamedm") String newnamedm){
        DMM.updateNomDM(oldnamedm,newnamedm);
        return Response
                .ok()
                .build();

    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("dminespace")
//    @RolesAllowed("admin")
    @PermitAll
    public Response getDmInEspace(@QueryParam(value = "nomDepartement") String nomDepartement)  {


        HashMap<String, List<String>> mapchambr = new HashMap<>();

        HashMap<Espace,List<DMItem>> hashlitespae = DMM.get_items_Espace(nomDepartement);
        for (Espace esp : hashlitespae.keySet()) {


            List<String> lstLits = new ArrayList<>();


            if(hashlitespae.get(esp) !=null) {
                for (DMItem dmitem : hashlitespae.get(esp)) {

                    lstLits.add(dmitem.toJsonn().toString());

                }

            }
            mapchambr.put(esp.toJsonn().toString(), lstLits);

        }
        return Response.ok().entity(mapchambr).build();
    }





    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("dminstock")
//    @RolesAllowed("admin")
    @PermitAll
    public Response getDmInStock(@QueryParam(value = "nomDepartement") String nomDepartement)  {


        HashMap<String, List<String>> mapchambr = new HashMap<>();

        HashMap<Espace,List<DMItem>> map_dm_espae = DMM.get_items_Espace(nomDepartement);
        for (Espace esp : map_dm_espae.keySet()) {

            if(Set.of(1001,1002,1003,1004).contains(esp.getNumero())){

                List<String> lstLits = new ArrayList<>();


                if (map_dm_espae.get(esp) != null) {
                    for (DMItem dmitem : map_dm_espae.get(esp)) {

                        lstLits.add(dmitem.toJsonn().toString());

                    }

                }
                mapchambr.put(esp.toJsonn().toString(), lstLits);

            }

        }
        return Response.ok().entity(mapchambr).build();
    }






    @PUT
    @Path("affecter_dm")
    public Response affecter_dm_espace(
            @QueryParam("id_dmItem") int id_dmItem,
            @QueryParam("id_espace") int id_escape
    ) {
//        TypeDM TypeDM = DMM.getTypeDmByName(typedm);

        DMM.affecter_DmItem(id_dmItem,id_escape);
        return Response
                .status(Response.Status.CREATED)
                .entity("DMIiem affected with successfull")
                .build();
    }



    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam(value = "file") InputStream uploadedInputStream,
            @FormDataParam(value = "filemetadata") FormDataContentDisposition fileDetail) {

        String uploadedFileLocation = path+"\\"+ fileDetail.getFileName();
        writeToFile(uploadedInputStream, uploadedFileLocation);
        String output = "File uploaded to : " + uploadedFileLocation;
        return Response.status(201).entity(output).build();
    }

    private void writeToFile(InputStream uploadedInputStream,
                             String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @POST
    @Path("uploadfile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleFileUpload(@FormDataParam(value = "file") InputStream file) throws IOException {


        FileOutputStream out=new FileOutputStream(path+File.separator+"imagee.png");

        out.write(file.readAllBytes());


        return Response.ok().entity("successful").build();
    }

}
