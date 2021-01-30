package com.illuminator.dao;

import com.illuminator.entity.main.Goal;
import org.hibernate.SessionFactory;

public class GoalDao extends GenericDao {
    public GoalDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Goal getByMetrikaId(Long metrikaId) {
        return getByMetrikaId(Goal.class, metrikaId);
    }

}
