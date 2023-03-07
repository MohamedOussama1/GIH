package ma.uiass.eia.pds;

import ma.uiass.eia.pds.model.Lit.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitEquipe;
import ma.uiass.eia.pds.model.Lit.TypeLit;
import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.departement.NomDepartement;
import ma.uiass.eia.pds.model.dm.Dm;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.chambre.TypeChambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;
import ma.uiass.eia.pds.model.espace.salle.TypeSalle;
import ma.uiass.eia.pds.persistance.GetSessionFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8081/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in ma.uiass.eia.pds package
        final ResourceConfig rc = new ResourceConfig().packages("ma.uiass.eia.pds.controller");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {

        // Test Hibernate
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        // Cardiologie
        Departement cardiologie = new Departement();
        cardiologie.setCapacity(200);
        cardiologie.setNomDepartement(NomDepartement.CARDIOLOGIE);

        // Batiment
        Batiment batiment1 = new Batiment();
        batiment1.setDepartement(cardiologie);
        batiment1.setNomBatiment("Batiment 1");

        Batiment batiment2 = new Batiment();
        batiment2.setDepartement(cardiologie);
        batiment2.setNomBatiment("Batiment 1");

        // Chambre et Salle
        Espace salle = new Salle(100, batiment2, TypeSalle.SALLE_EXAMINATION);
        Espace chambre = new Chambre(200, batiment1, TypeChambre.DOUBLE);


        // LitEquipe
        LitEquipe litEquipeSalle = new LitEquipe();
        litEquipeSalle.setType(TypeLit.ELECTRIC);
        litEquipeSalle.setEtat(EtatLit.DISPONIBLE);
        litEquipeSalle.setEspace(salle);

        LitEquipe litEquipeChambre = new LitEquipe();
        litEquipeChambre.setType(TypeLit.ELECTRIC);
        litEquipeChambre.setEtat(EtatLit.DISPONIBLE);
        litEquipeChambre.setEspace(chambre);

        // Dm
        Dm ciseaux = new Dm();
        ciseaux.setNom("ciseaux");
        ciseaux.setLit(litEquipeChambre);
        Dm scanner = new Dm();
        scanner.setNom("scanner");
        scanner.setLit(litEquipeSalle);


        // Save entities
        session.save(cardiologie);
        session.save(batiment1);
        session.save(batiment2);
        session.save(salle);
        session.save(chambre);
        session.save(litEquipeSalle);
        session.save(litEquipeChambre);
        session.save(ciseaux);
        session.save(scanner);

        // Commit and close session
        session.getTransaction().commit();
        session.close();



        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }
}

