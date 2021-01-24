package dao;

import org.hibernate.SessionFactory;

public class SourceDao extends GenericDao {
    public SourceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
