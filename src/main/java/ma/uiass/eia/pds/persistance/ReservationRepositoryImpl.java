package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.reservation.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
}
