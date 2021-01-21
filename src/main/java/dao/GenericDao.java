package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class GenericDao {

    private final SessionFactory sessionFactory;

    public GenericDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> T get(Class<T> clazz, Serializable id) { return getSession().get(clazz, id); }

    public <T> T merge(Object object) { return (T) getSession().merge(object); }

    public <T> List<T> getAll(Class<T> clazz) {
        return getSession()
                .createQuery("From " + clazz.getSimpleName(), clazz)
                .getResultList();
    }

    public void update (Object obj) {
        if (obj == null) {
            return;
        }
        getSession().update(obj);
    }

    public void save(Object obj) {
        if (obj == null) {
           return;
        }
        getSession().save(obj);
    }

    protected Session getSession() { return sessionFactory.getCurrentSession(); }

}
