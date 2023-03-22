package ma.uiass.eia.pds;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.Lit.enums.FonctionLit;
import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.batiment.Batiment;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.chambre.TypeChambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;
import ma.uiass.eia.pds.model.espace.salle.TypeSalle;
import ma.uiass.eia.pds.model.etage.Etage;
import ma.uiass.eia.pds.model.patient.Patient;
import ma.uiass.eia.pds.model.reservation.Reservation;
import ma.uiass.eia.pds.persistance.GetSessionFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

//         Populate DB

        Batiment batimentA = new Batiment("BatimentA");
        Batiment batimentB = new Batiment("BatimentB");

        Etage etageA1 = new Etage(1, batimentA);
        Etage etageA2 =new Etage(2, batimentA);
        Etage etageA3 =new Etage(3, batimentA);

        Etage etageB1 = new Etage(1, batimentB);
        Etage etageB2 =new Etage(2, batimentB);
        Etage etageB3 =new Etage(3, batimentB);

        Departement cardiologie = new Departement("Cardiologie", 200,  etageA1);
        Departement radiologie = new Departement("Radiologie", 100, etageA1);

        Departement neurologie = new Departement("Neurologie", 50, etageA2);
        Departement oncologie = new Departement("Oadiologie", 40,  etageA3);

        Espace salleExamination = new Salle("Examination",10, 20, cardiologie, TypeSalle.SALLE_EXAMINATION);
        Espace salleReanimation = new Salle("reveil",12,  30, cardiologie, TypeSalle.SALLE_REVEIL );

        Espace salleExma = new Salle("Exam",15,  20, neurologie, TypeSalle.SALLE_EXAMINATION);
        Espace salleReanim = new Salle("rev", 14,30, neurologie, TypeSalle.SALLE_REVEIL );

        Espace chambre1 = new Chambre("ch1",41,  30, cardiologie, TypeChambre.MULTI );
        Espace chambre2 = new Chambre("ch2",68,  30, cardiologie, TypeChambre.MULTI );
        Espace chambre3 = new Chambre("ch3",70,  10, cardiologie, TypeChambre.SINGLE );

        Lit litCardiologie = new Lit(TypeLit.ELECTRIQUE, ModelLit.STANDARD, "200x150x50", 150, Period.of(5, 0, 0), 11000,"Lit standard");
        Lit litCardiologie1 = new Lit(TypeLit.ELECTRIQUE, ModelLit.STANDARD, "200x150x50", 150, Period.of(5, 0, 0), 11000,"Lit standard");
        Lit litCardiologie2 = new Lit(TypeLit.ELECTRIQUE, ModelLit.STANDARD, "200x150x50", 150, Period.of(5, 0, 0), 11000,"Lit standard");
        Lit litCardiologie3 = new Lit(TypeLit.ELECTRIQUE, ModelLit.STANDARD, "200x150x50", 150, Period.of(5, 0, 0), 11000,"Lit standard");
        Lit litCardiologie4 = new Lit(TypeLit.ELECTRIQUE, ModelLit.STANDARD, "200x150x50", 150, Period.of(5, 0, 0), 11000,"Lit standard");
        Set fonctions = new HashSet();
        fonctions.addAll(Arrays.asList(FonctionLit.INCLINAISON_LIT, FonctionLit.POSITION_ASSISE, FonctionLit.TRENDELENBURG));
        litCardiologie.setFonctionsLit(fonctions);
        LitItem litItem = new LitItem("202", litCardiologie, chambre3);
        LitItem litItem2 = new LitItem("221", litCardiologie1, salleReanim);
        LitItem litItem3 = new LitItem("301", litCardiologie2, salleReanimation);
        LitItem litItem5 = new LitItem("21", litCardiologie3, chambre1);
        LitItem litItem4 = new LitItem("20", litCardiologie4, chambre2);
        Patient patient = new Patient("JARMOUNI", "Rachid", LocalDate.of(2002, 1, 1), 80, 175);
        Reservation reservation = new Reservation(LocalDateTime.of(2023, 3, 17, 12, 30, 0), LocalDateTime.of(2023, 3, 18, 12, 30, 0), litItem, patient);

        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(batimentA);
        session.save(batimentB);
        session.save(etageA1);
        session.save(etageB1);
        session.save(etageA2);
        session.save(etageB2);
        session.save(etageA3);
        session.save(etageB3);
        session.save(cardiologie);
        session.save(neurologie);
        session.save(radiologie);
        session.save(oncologie);
        session.save(salleExamination);
        session.save(salleReanimation);
        session.save(salleReanim);
        session.save(salleExma);
        session.save(chambre1);
        session.save(chambre2);
        session.save(chambre3);
        session.save(litCardiologie);
        session.save(litCardiologie1);
        session.save(litCardiologie2);
        session.save(litCardiologie3);
        session.save(litCardiologie4);
        session.save(litItem);
        session.save(litItem2);
        session.save(litItem3);
        session.save(litItem4);
        session.save(litItem5);
        session.save(patient);
        session.save(reservation);
        session.getTransaction().commit();
        session.close();

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }
}

