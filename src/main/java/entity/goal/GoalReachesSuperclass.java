package entity.goal;

import entity.main.Counter;
import entity.main.Goal;
import utils.StandardMethodGenerator;

import java.time.LocalDate;
import java.util.Objects;

@MappedSupperclass
public class GoalReachesSuperclass {

    private LocalDate date;
    private double reaches;

    @ManyToOne
    @JoinColumn(name = "goalid")
    private Goal goal;

    @ManyToOne
    @JoinColumn(name = "counterid")
    private Counter counter;

    @Override
    public String toString() {
        return StandardMethodGenerator.generateToStringMethod(this);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                sourceSuperclass.getMetrikaId() +
                        date.toString() +
                        goal.getId()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(getClass() == obj.getClass())) return false;
        GoalReachesSuperclass grs = (GoalReachesSuperclass) obj;
        return Objects.equals(goal.getId(), grs.goal.getId()) &&
                Objects.equals(sourceSuperclass.getMetrikaId(), grs.sourceSuperclass.getMetrikaId()) &&
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

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }


}
