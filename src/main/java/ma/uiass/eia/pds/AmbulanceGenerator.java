package ma.uiass.eia.pds;

import ma.uiass.eia.pds.model.Ambulance.Ambulance;
import ma.uiass.eia.pds.model.Ambulance.AmbulanceType;
import ma.uiass.eia.pds.model.Ambulance.Revision;
import ma.uiass.eia.pds.model.Ambulance.TypeRevision;
import ma.uiass.eia.pds.persistance.GetSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AmbulanceGenerator {
    private static final String[] REVISION_TYPES = {"S", "CD", "LD"};
    private static final String[] REVISION_STATUSES = {"F", "NFCD", "NFLD"};
    private static final String AMBULANCE_TYPE = "SECOURS_URGENCE";

    private static String generateRandomName(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(letters.length());
            stringBuilder.append(letters.charAt(index));
        }

        return stringBuilder.toString();
    }

    private static LocalDate randomDate(LocalDate startDate, LocalDate endDate) {
        Random random = new Random();
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay));

        return LocalDate.ofEpochDay(randomDay);
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Ambulance> ambulanceInstances = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            String ambulanceName = generateRandomName(5);
            LocalDate currentDate = LocalDate.now();
            int mileage = new Random().nextInt(4900) + 100; // Random mileage between 100 and 5000
            String brand = "Ford";

            Ambulance ambulanceInstance = new Ambulance(ambulanceName, currentDate, mileage, AmbulanceType.SOINS_DE_BASE, brand);

            String revisionType = null;
            String revisionStatus = REVISION_STATUSES[new Random().nextInt(REVISION_STATUSES.length)];
            if (revisionStatus.equals("F"))
                revisionType = "S";
            if (revisionStatus.equals("NFCD"))
                revisionType = "CD";
            if (revisionStatus.equals("NFLD"))
                revisionType = "LD";
            LocalDate startDate = randomDate(currentDate.minusYears(1), currentDate);
            LocalDate endDate = randomDate(startDate, currentDate);
            if (revisionType.equals("S"))
                endDate = startDate;
            if (revisionType.equals("CD"))
                endDate = endDate.plusMonths(2);
            if(revisionType.equals("LD"))
                endDate = endDate.plusMonths(5);

            Revision revisionInstance = new Revision(startDate, ambulanceInstance, TypeRevision.valueOf(revisionType), revisionStatus);
            revisionInstance.setEndDate(endDate);
            revisionInstance.setEtatAmbulance_1("F");

            ambulanceInstance.setRevision(revisionInstance);
            ambulanceInstances.add(ambulanceInstance);
            session.save(ambulanceInstance);
            session.save(revisionInstance);
        }
        session.getTransaction().commit();
        session.close();

        // Printing the first few instances for demonstration
        for (int i = 0; i < 5; i++) {
            Ambulance ambulanceInstance = ambulanceInstances.get(i);
            Revision revisionInstance = ambulanceInstance.getRevision();

            System.out.println("Ambulance Name: " + ambulanceInstance.getImmatriculation());
            System.out.println("Current Date: " + ambulanceInstance.getDate_mise_service());
            System.out.println("Mileage: " + ambulanceInstance.getKm());
            System.out.println("Ambulance Type: " + ambulanceInstance.getAmbulanceType());
            System.out.println("Brand: " + ambulanceInstance.getModel());
            System.out.println("Revision Start Date: " + revisionInstance.getStartDate());
            System.out.println("Revision End Date: " + revisionInstance.getEndDate());
            System.out.println("Revision Type: " + revisionInstance.getTypeRevision());
            System.out.println("Revision Status: " + revisionInstance.getEtatAmbulance_0());
            System.out.println("---");
        }
    }
}

