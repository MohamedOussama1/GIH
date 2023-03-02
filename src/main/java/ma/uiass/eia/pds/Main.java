package ma.uiass.eia.pds;

import ma.uiass.eia.pds.model.EtatLit;
import ma.uiass.eia.pds.model.Lit;
import ma.uiass.eia.pds.model.TypeLit;
import ma.uiass.eia.pds.persistance.GetSessionFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
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
        final ResourceConfig rc = new ResourceConfig().packages("ma.uiass.eia.pds");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//        Session session = GetSessionFactory.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.save(new Lit(EtatLit.OCCUPE, TypeLit.STANDARD));
//        session.save(new Lit(EtatLit.DEFECTUEUX, TypeLit.ELECTRIC));
//        session.save(new Lit(EtatLit.EN_STOCK, TypeLit.ELECTRIC));
//        session.getTransaction().commit();
//        session.close();
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }
}

