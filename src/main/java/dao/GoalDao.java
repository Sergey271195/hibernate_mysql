package dao;

import org.hibernate.SessionFactory;

public class GoalDao extends GenericDao {
    public GoalDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
