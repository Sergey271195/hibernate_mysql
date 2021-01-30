package com.illuminator.dao;

import com.illuminator.entity.source.SourceSuperclass;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;

public class SourceDao extends GenericDao {

    public SourceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public <T extends SourceSuperclass> T createOrFetchSource(T source) {
        if (source.getName().length() >= 500) {
            String truncatedName = source.getName().substring(0, 490);
            source.setName(truncatedName);
            source.setMetrikaId(Objects.hash(truncatedName));
        }
        Serializable metrikaId = (Serializable) source.getMetrikaId();
        T managedSource = (T) getByMetrikaId(source.getClass(), metrikaId);
        if (managedSource == null) {
            save(source);
            System.out.println("Inserting new source: " + source);
            return source;
        }
        return managedSource;
    }

    public <T extends SourceSuperclass> T getNewSourceInstance(Class<? extends SourceSuperclass> dimension) {
        try {
            return (T) dimension.getDeclaredConstructor()
                    .newInstance();
        } catch (InstantiationException |
                IllegalAccessException |
                InvocationTargetException |
                NoSuchMethodException err) {
            err.printStackTrace();
            return null;
        }
    }

    public <T extends SourceSuperclass> T createOrFetchSourceFromData(
            Class<? extends SourceSuperclass> dimension, Map<String, String> sourceData
    ) {
        T newSourceInstance = getNewSourceInstance(dimension);
        T filledSourceInstance = (T) newSourceInstance.createSourceFromMetrikData(sourceData);
        return createOrFetchSource(filledSourceInstance);
    }

}
