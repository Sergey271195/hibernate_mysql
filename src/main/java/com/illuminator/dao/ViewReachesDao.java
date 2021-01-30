package com.illuminator.dao;

import com.illuminator.entity.ReachesSuperclass;
import org.hibernate.SessionFactory;

import java.lang.reflect.InvocationTargetException;

public class ViewReachesDao extends GenericDao {
    public ViewReachesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public  <T extends ReachesSuperclass> T getTableRowInstance(Class<? extends ReachesSuperclass> insertTable) {
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
