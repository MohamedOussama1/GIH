package ma.uiass.eia.pds;

import jakarta.ws.rs.ext.MessageBodyWriter;
import ma.uiass.eia.pds.model.Lit.Dimensions;
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
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
        ResourceConfig rc = new ResourceConfig().packages("ma.uiass.eia.pds.controller", "com.fasterxml.jackson.jaxrs.json.provider").register(JacksonFeature.class);

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
        Departement oncologie = new Departement("Oncologie", 40,  etageA3);

        Espace salleExamination = new Salle("Salle de procédure NEURO",912, 20, neurologie, TypeSalle.SALLE_PROCEDURE);
        Espace salleReanimation = new Salle("Salle de réveil NEURO",922,  30, neurologie, TypeSalle.SALLE_REVEIL );

        Espace salleExam = new Salle("Salle d'éxamination NEURO",971,  20, neurologie, TypeSalle.SALLE_EXAMINATION);
        Espace salleReanim = new Salle("Salle de radiologie NEURO", 906,30, radiologie, TypeSalle.SALLE_RADIOLOGIE );

        Espace salleExamination1 = new Salle("Salle de procédure ONCO",1005, 20, oncologie, TypeSalle.SALLE_PROCEDURE);
        Espace salleReanimation1 = new Salle("Salle de d'infusion ONCO",929,  30, oncologie, TypeSalle.SALLE_INFUSION);

        Espace salleExam1 = new Salle("Salle d'éxamination CARDIO",905,  20, cardiologie, TypeSalle.SALLE_EXAMINATION);
        Espace salleExam2 = new Salle("Salle de réveil CARDIO",800,  20, cardiologie, TypeSalle.SALLE_REVEIL);
        Espace salleExam3 = new Salle("Salle de procédure CARDIO",801,  20, cardiologie, TypeSalle.SALLE_PROCEDURE);
        Espace salleExam4 = new Salle("Salle d'éxamination CARDIO",802,  20, cardiologie, TypeSalle.SALLE_EXAMINATION);
        Espace salleReanim1 = new Salle("Salle de réveil CARDIOO", 1006,30, cardiologie, TypeSalle.SALLE_REVEIL);

        Espace stock = new Salle("Stock", 1000,200, null, TypeSalle.SALLE_EXAMINATION );
        Espace stockRadio = new Salle("Stock Radiologie", 1004,200, radiologie, TypeSalle.SALLE_EXAMINATION );
        Espace stockOnco = new Salle("Stock Oncologie", 1003,200, oncologie, TypeSalle.SALLE_EXAMINATION );
        Espace stockCardio = new Salle("Stock Cardiologie", 1002,200, cardiologie, TypeSalle.SALLE_EXAMINATION );
        Espace stockNeuro = new Salle("Stock Neurologie", 1001,200, neurologie, TypeSalle.SALLE_EXAMINATION );

        Espace chambre1 = new Chambre("chambre4 NEURO",1090,  30, neurologie, TypeChambre.MULTI );
        Espace chambre2 = new Chambre("chambre2 NEURO",708,  30, neurologie, TypeChambre.DOUBLE );
        Espace chambre3 = new Chambre("chambre1 NEURO",607,  10, neurologie, TypeChambre.SINGLE );

        Espace chambre4 = new Chambre("chambre4 CARDIO",1080,  30, cardiologie, TypeChambre.MULTI );
        Espace chambre5 = new Chambre("chambre2 CARDIO",710,  30, cardiologie, TypeChambre.DOUBLE );
        Espace chambre6 = new Chambre("chambre1 CARDIO",601,  10, cardiologie, TypeChambre.SINGLE );

        Espace chambre7 = new Chambre("chambre4 RADIO",822,  30, radiologie, TypeChambre.MULTI );
        Espace chambre8 = new Chambre("chambre2 RADIO",733,  30, radiologie, TypeChambre.DOUBLE );
        Espace chambre9 = new Chambre("chambre1 RADIO",644,  10, radiologie, TypeChambre.SINGLE );

        Set<FonctionLit> fonctionLits = new HashSet<>();
        fonctionLits.addAll(Arrays.asList(FonctionLit.REGLAGE_DE_LA_HAUTEUR, FonctionLit.ANTI_TRENDELENBURG, FonctionLit.BARRIERES_SECURITE));

        Lit litDescription = new Lit(TypeLit.ELECTRIQUE, ModelLit.STANDARD, new Dimensions(200, 100, 50), 150, Period.of(1, 0, 0), 11000, fonctionLits, "#0099cc", "Lit électrique standard à 3 fonctions fournit par Hill Room");
        Lit litDescription1 = new Lit(TypeLit.ELECTRIQUE, ModelLit.ROTATION, new Dimensions(200, 100, 50), 180, Period.of(3, 0, 0), 21000, fonctionLits, "#958634","Lit électrique rotation à 3 fonctions fournit par Invacare");
        Lit litDescription2 = new Lit(TypeLit.MECANIQUE, ModelLit.TRAITEMENT, new Dimensions(200, 90, 50), 200, Period.of(2, 0, 0), 14000, fonctionLits, "#279894","Lit mécanique traitement à 3 fonctions fournit par Linet");
        Lit litDescription3 = new Lit(TypeLit.ELECTRIQUE, ModelLit.STANDARD, new Dimensions(200, 70, 50), 300, Period.of(5, 0, 0), 41000, fonctionLits, "#6addcc","Lit électrique standard à 3 fonctions fournit par Arjo");
        Lit litDescription4 = new Lit(TypeLit.MECANIQUE, ModelLit.STANDARD, new Dimensions(200, 85, 50), 200, Period.of(3, 0, 0), 18000, fonctionLits, "#dadd6a","Lit mécanique standard à 3 fonctions fournit par Favero");

        LitItem litItem = new LitItem("23", litDescription, stock);
        LitItem litItem15 = new LitItem("28", litDescription, salleExamination1);
        LitItem litItem2 = new LitItem("106", litDescription1, salleReanim);
        LitItem litItem3 = new LitItem("908", litDescription2, stock);
        LitItem litItem5 = new LitItem("323", litDescription3, chambre1);
        LitItem litItem16 = new LitItem("22", litDescription, stock);
        LitItem litItem17 = new LitItem("27", litDescription, salleExam1);
        LitItem litItem18= new LitItem("105", litDescription1, salleExam4);
        LitItem litItem19 = new LitItem("907", litDescription2, salleExam3);
        LitItem litItem20 = new LitItem("322", litDescription3, salleExam2);
        LitItem litItem4 = new LitItem("221", litDescription4, chambre2);
        LitItem litItem6 = new LitItem("112", litDescription, stock);
        LitItem litItem7 = new LitItem("382", litDescription1, stockRadio);
        LitItem litItem8 = new LitItem("341", litDescription2, salleReanimation1);
        LitItem litItem9 = new LitItem("293", litDescription3, stockNeuro);
        LitItem litItem10 = new LitItem("119", litDescription4, chambre5);
        LitItem litItem11 = new LitItem("109", litDescription4, chambre6);
        LitItem litItem12 = new LitItem("12", litDescription4, chambre7);
        LitItem litItem13 = new LitItem("7", litDescription4, stockCardio);
        LitItem litItem14 = new LitItem("9", litDescription4, stockOnco);

        Patient patient = new Patient("Jarmouni", "Rachid", LocalDate.of(2002, 1, 1), 80, 175);
        Reservation reservation = new Reservation(LocalDateTime.of(2023, 3, 10, 0, 0, 0), LocalDateTime.of(2023, 3, 17, 12, 30, 0), LocalDateTime.of(2023, 3, 18, 12, 30, 0), litItem, patient);

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
        session.save(salleReanimation1);
        session.save(salleReanim);
        session.save(salleReanim1);
        session.save(salleExam);
        session.save(chambre1);
        session.save(chambre2);
        session.save(chambre3);
        session.save(chambre4);
        session.save(chambre5);
        session.save(chambre6);
        session.save(chambre7);
        session.save(chambre8);
        session.save(chambre9);
        session.save(salleExamination1);
        session.save(salleExam1);
        session.save(salleExam2);
        session.save(salleExam3);
        session.save(salleExam4);
        session.save(salleReanim1);
        session.save(salleReanimation1);
        session.save(stock);
        session.save(stockCardio);
        session.save(stockRadio);
        session.save(stockOnco);
        session.save(stockNeuro);
        session.save(litDescription);
        session.save(litDescription1);
        session.save(litDescription2);
        session.save(litDescription3);
        session.save(litDescription4);
        session.save(litItem);
        session.save(litItem2);
        session.save(litItem3);
        session.save(litItem4);
        session.save(litItem5);
        session.save(litItem6);
        session.save(litItem7);
        session.save(litItem8);
        session.save(litItem9);
        session.save(litItem10);
        session.save(litItem11);
        session.save(litItem12);
        session.save(litItem13);
        session.save(litItem14);
        session.save(litItem15);
        session.save(litItem16);
        session.save(litItem17);
        session.save(litItem18);
        session.save(litItem19);
        session.save(litItem20);
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

