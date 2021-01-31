package com.illuminator.dao;

import com.illuminator.entity.goal.GoalReachesSuperclass;
import org.hibernate.SessionFactory;

import javax.persistence.Tuple;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

public class GoalReachesDao extends GenericDao {
    public GoalReachesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public  <T extends GoalReachesSuperclass> T getTableRowInstance(Class<? extends GoalReachesSuperclass> insertTable) {
        try {
            return (T) insertTable.getDeclaredConstructor()
                    .newInstance();
        } catch (InstantiationException |
                IllegalAccessException |
                InvocationTargetException |
                NoSuchMethodException err) {
            err.printStackTrace();
            return null;
        }
    }

    public List<Tuple> getAggregateDataForPeriod(Long id) {
        List<Tuple> aggregateDto = getSession().createNativeQuery(
        "WITH r AS (" +
                "SELECT " +
                "sum(reaches) as count, " +
                "goalid, " +
                "sourceid " +
                "FROM search_engine_goal " +
                "WHERE counterid = :id AND date BETWEEN :date1 AND :date2 " +
            "GROUP BY goalid, sourceid " +
                    ") SELECT " +
                    "r.count, s.name, a.name as goal_name " +
            "FROM r LEFT JOIN goal a " +
            "ON r.goalId = a.id " +
            "LEFT JOIN search_engine s ON r.sourceid = s.id ORDER BY goal_name, r.count DESC;",
                Tuple.class)
                .setParameter("id", id)
                .setParameter("date1", LocalDate.parse("2017-01-01"))
                .setParameter("date2", LocalDate.parse("2021-01-30"))
                .getResultList();
        return aggregateDto;
    }
}
