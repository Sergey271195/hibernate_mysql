package dao;

import entity.ApplicationProperties;
import entity.main.Counter;
import org.hibernate.SessionFactory;

public class CounterDao extends GenericDao {

    public CounterDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Counter getByMetrikaId(Long metrikaId) {
        System.out.println(metrikaId);
        Counter counter =  getSession().createQuery(
            "FROM Counter counter WHERE counter.metrikaId = :metrikaId", Counter.class)
            .setParameter("metrikaId", metrikaId)
            .getSingleResult();
        System.out.println("COUNTER: " + counter);
        return counter;
    }

}
