import entity.AdvEngine;
import entity.EntityClassRegistry;
import entity.ReferralSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.Objects;


public class Entrypoint {

    public static void main(String[] args) {
        SessionFactory sessionFactory;
        StandardServiceRegistry serviceRegistry = new
                StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        EntityClassRegistry.ENTITY_CLASS_REGISTRY.forEach(metadataSources::addAnnotatedClass);
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM AdvEngine");
        query.getResultList().forEach(r -> System.out.println(r));

        session.getTransaction().commit();
        session.close();
    }

}
