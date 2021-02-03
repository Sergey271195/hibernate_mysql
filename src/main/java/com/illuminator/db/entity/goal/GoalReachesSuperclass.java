package com.illuminator.db.entity.goal;

import com.illuminator.db.entity.ReachesSuperclass;
import com.illuminator.db.entity.main.Goal;
import com.illuminator.db.entity.source.SourceSuperclass;
import com.illuminator.db.utils.StandardMethodGenerator;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Objects;


@MappedSuperclass
public class GoalReachesSuperclass<T extends SourceSuperclass> extends ReachesSuperclass<T> {

    private Goal goal;

    @ManyToOne
    @JoinColumn(name = "goalid")
    public Goal getGoal() {
        return goal;
    }

    @Override
    public String toString() {
        return StandardMethodGenerator.generateToStringMethod(this);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                goal.getId() + super.hashCode()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(getClass() == obj.getClass())) return false;
        GoalReachesSuperclass grs = (GoalReachesSuperclass) obj;
        return Objects.equals(goal.getId(), grs.goal.getId()) && super.equals(obj);
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

}
