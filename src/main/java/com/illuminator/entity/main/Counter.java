package com.illuminator.entity.main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;
import com.illuminator.utils.StandardMethodGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "counter")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "goals"})
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NaturalId
    @Column(name= "metrika_id")
    private long metrikaId;

    @Column(name = "counter_url")
    private String counterUrl;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    private String name;
    @Column(columnDefinition = "bit(1) default 0")
    private boolean commercial;
    @Column(columnDefinition = "bit(1) default 1")
    private boolean relevant;

    @OneToMany(mappedBy = "counter", fetch = FetchType.LAZY)
    private Set<Goal> goals;

    @Override
    public String toString() {
        return "Counter=[id=" + id +
                ", metrikaid=" + metrikaId +
                ", counterUrl=" + counterUrl +
                ", creationDate=" + creationDate +
                ", name=" + name +
                ", commercial=" + commercial +
                ", relevant=" + relevant  + ']';
    }

    @Override
    public int hashCode() { return Objects.hash(name); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj.getClass() == getClass())) return false;
        Counter counter = getClass().cast(obj);
        return Objects.equals(metrikaId, counter.metrikaId)
                && Objects.equals(name, counter.name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCounterUrl() {
        return counterUrl;
    }

    public void setCounterUrl(String counterUrl) {
        this.counterUrl = counterUrl;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCommercial() {
        return commercial;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
    }

    public boolean isRelevant() {
        return relevant;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }

    public Set<Goal> getGoals() {
        return goals;
    }

    public void setGoals(Set<Goal> goals) {
        this.goals = goals;
    }

    public long getMetrikaId() {
        return metrikaId;
    }

    public void setMetrikaId(long metrikaId) {
        this.metrikaId = metrikaId;
    }
}
