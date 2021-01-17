package entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public class SourceSuperclass<T> {

    private T metrikaId;
    private String name;

    @NaturalId
    @Column(name = "metrika_id", nullable = false, unique = true)
    private T getMetrikaId() {return metrikaId;}

    private void setMetrikaId(T metrikaId) {this.metrikaId = metrikaId;}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String toStringInheritedFields() {
        return ", metrika_id = " + metrikaId + ", name = " + name + ']';
    }

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
