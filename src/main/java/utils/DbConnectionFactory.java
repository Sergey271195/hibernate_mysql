package utils;

import entity.EntityClassRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DbConnectionFactory {

    public static SessionFactory getSessionFactory() {
        StandardServiceRegistry serviceRegistry = new
                StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        EntityClassRegistry.ENTITY_SOURCE_CLASS_REGISTRY.forEach(metadataSources::addAnnotatedClass);
        EntityClassRegistry.ENTITY_MAIN_CLASS_REGISTRY.forEach(metadataSources::addAnnotatedClass);
        EntityClassRegistry.ENTITY_GOAL_REACHES_REGISTRY.forEach(metadataSources::addAnnotatedClass);
        EntityClassRegistry.ENTITY_VIEW_REACHES_REGISTRY.forEach(metadataSources::addAnnotatedClass);
        EntityClassRegistry.ENTITY_PRICE_REACHES_REGISTRY.forEach(metadataSources::addAnnotatedClass);
        EntityClassRegistry.ENTITY_PURCHASE_REACHES_REGISTRY.forEach(metadataSources::addAnnotatedClass);
        return metadataSources.buildMetadata().buildSessionFactory();
    }

}
