package com.illuminator.dao;

import com.illuminator.entity.goal.GoalReachesSuperclass;
import org.hibernate.SessionFactory;

import java.lang.reflect.InvocationTargetException;

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
}
