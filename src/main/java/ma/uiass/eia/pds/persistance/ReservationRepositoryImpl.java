package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.reservation.Reservation;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository{
    private SessionFactory sessionFactory = GetSessionFactory.getSessionFactory();
    @Override
    public List<Reservation> getReservationsLit(int idLit) {

        // Create Query
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteria = criteriaBuilder.createQuery(Reservation.class);

        // Write Query
        Root<Reservation> root = criteria.from(Reservation.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get("lit"), idLit));
        criteria.orderBy(criteriaBuilder.asc(root.get("dateDebut")));

        // Execute Query
        List<Reservation> reservations = session.createQuery(criteria).getResultList();

        return reservations;
    }

    @Override
    public List<Reservation> getAll() {
        Session session=sessionFactory.openSession();

        CriteriaBuilder builder=session.getCriteriaBuilder();


        CriteriaQuery<Reservation> criteriaq=builder.createQuery(Reservation.class);

        Root<Reservation> root=criteriaq.from(Reservation.class);


        criteriaq.select(root);

        List<Reservation> lst=session.createQuery(criteriaq).getResultList();

        return lst;
    }



    @Override
    public List<Reservation> getCurrentAll() {
        List<Reservation> currentReservations = new ArrayList<>();
        try {
            // Create a new Hibernate session
            Session session = sessionFactory.openSession();

            // Create a new Criteria object for the Reservation class
            Criteria criteria = session.createCriteria(Reservation.class);

            // Add a condition to the Criteria to only return reservations with an end date greater than the current date
            Date currentDate = new Date();
            criteria.add(Restrictions.gt("endDate", currentDate));

            // Execute the Criteria and get the list of matching reservations
            currentReservations = criteria.list();

            // Close the Hibernate session
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return currentReservations;
    }




    @Override
    public Reservation findReservationById(int id_reservation) {
        return null;
    }





    @Override
    public void saveReservation(int idlit) {

        Session session=sessionFactory.openSession();

        session.beginTransaction();

        LitItem lit=session.find(LitItem.class,idlit);

        Reservation reservation = new Reservation(LocalDateTime.now(), LocalDateTime.of(2023, 3, 18, 12, 30, 0),null, lit);

        session.save(reservation);

        session.getTransaction().commit();

        session.close();

    }
    @Override
    public void saveExitDate(int idLit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteria = builder.createQuery(Reservation.class);

        Root<Reservation> root = criteria.from(Reservation.class);

        Predicate predicate1 = builder.equal(root.get("lit"), idLit);
        Predicate predicate2 = builder.isNull(root.get("dateFinPredicted"));

        criteria.select(root)
                .where(builder.and(predicate1, predicate2));

        Reservation currentReservation = session.createQuery(criteria).getSingleResult();
        currentReservation.setDateFin(LocalDateTime.now());
        session.save(currentReservation);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void Update_Les_Dates_Reel() {

    }


}
