package com.illuminator.db.entity;

import com.illuminator.db.entity.main.Counter;
import com.illuminator.db.entity.source.SourceSuperclass;
import com.illuminator.db.utils.StandardMethodGenerator;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public class ReachesSuperclass<T extends SourceSuperclass> {

    private LocalDate date;
    private double reaches;

    private T sourceSuperclass;
    private Counter counter;

    @ManyToOne
    @JoinColumn(name = "counterid")
    public Counter getCounter() {
        return counter;
    }

    @ManyToOne
    @JoinColumn(name = "sourceid")
    public T getSourceSuperclass() {
        return sourceSuperclass;
    };

    @Override
    public String toString() {
        return StandardMethodGenerator.generateToStringMethod(this);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                sourceSuperclass.getMetrikaId() + date.toString()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(getClass() == obj.getClass())) return false;
        ReachesSuperclass grs = (ReachesSuperclass) obj;
        return Objects.equals(sourceSuperclass.getMetrikaId(), grs.sourceSuperclass.getMetrikaId()) &&
                Objects.equals(date, grs.date);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getReaches() {
        return reaches;
    }

    public void setReaches(double reaches) {
        this.reaches = reaches;
    }


    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public void setSourceSuperclass(T sourceSuperclass) {
        this.sourceSuperclass = sourceSuperclass;
    }

}
