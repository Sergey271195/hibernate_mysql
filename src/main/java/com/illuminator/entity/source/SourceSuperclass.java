package com.illuminator.entity.source;

import org.hibernate.annotations.NaturalId;
import com.illuminator.utils.StandardMethodGenerator;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@MappedSuperclass
public abstract class SourceSuperclass<T> {

    private T metrikaId;
    protected String name;

    @NaturalId
    @Column(name = "metrika_id", nullable = false, unique = true)
    public T getMetrikaId() {return metrikaId;}

    public void setMetrikaId(T metrikaId) {this.metrikaId = metrikaId;}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public abstract SourceSuperclass createSourceFromMetrikData(Map<String, String> sourceData);

    protected static <T extends SourceSuperclass> T createSource(
            Map<String, String> sourceData, T sourceInstance
    ) {
        String name = sourceData.get("name");
        String metrikaId = sourceData.get("id");
        Object metrikaNaturalId = metrikaId == null ? Objects.hash(name) : sourceData.get("id");
        sourceInstance.setName(name);
        sourceInstance.setMetrikaId(metrikaNaturalId);
        return sourceInstance;
    }

    @Override
    public String toString() { return StandardMethodGenerator.generateToStringMethod(this); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o.getClass() == getClass())) return false;
        SourceSuperclass clazz = getClass().cast(o);
        return Objects.equals(metrikaId, clazz.metrikaId);
    }

    @Override
    public int hashCode() { return Objects.hash(metrikaId); }

}
