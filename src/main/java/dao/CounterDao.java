package dao;

import entity.ApplicationProperties;
import entity.main.Counter;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Counter> getAll() {
        return super.getAll(Counter.class);
    }

    public List<Counter> getAllEager() {
        return ApplicationProperties.relevantCounters.stream()
                .map(this::getEager).collect(Collectors.toList());
    }

    public Counter getEager(Long counterId) {
        return getSession().createQuery(
                "from Counter counter " +
                        "left join fetch counter.goals " +
                        "where counter.metrikaId = :metrikaId", Counter.class
        ).setParameter("metrikaId", counterId)
        .getSingleResult();
    }

}
