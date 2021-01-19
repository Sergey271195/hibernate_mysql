package entity.source;

import org.hibernate.annotations.NaturalId;
import utils.StandardMethodGenerator;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public class SourceSuperclass<T> {

    private T metrikaId;
    protected String name;

    @NaturalId
    @Column(name = "metrika_id", nullable = false, unique = true)
    public T getMetrikaId() {return metrikaId;}

    public void setMetrikaId(T metrikaId) {this.metrikaId = metrikaId;}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

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
