package entity.main;

import utils.StandardMethodGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "metrika_id")
    private long metrikaId;
    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(name = "counter")
    private Counter counter;

    @Override
    public String toString() {
        return StandardMethodGenerator.generateToStringMethod(this);
    }

    @Override
    public int hashCode() {return Objects.hash(metrikaId); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(getClass() == obj.getClass())) return false;
        Goal goal = this.getClass().cast(obj);
        return Objects.equals(metrikaId, goal.metrikaId)
                && Objects.equals(name, goal.name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public long getMetrikaId() {
        return metrikaId;
    }

    public void setMetrikaId(long metrikaId) {
        this.metrikaId = metrikaId;
    }
}
