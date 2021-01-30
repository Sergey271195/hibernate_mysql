package com.illuminator.entity.goal;

import com.illuminator.entity.source.AdvEngine;

import javax.persistence.*;

@Entity
@Table(
        name = "adv_engine_goal",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"goalid", "sourceid", "date"})}
)
public class AdvEngineGoalReaches extends GoalReachesSuperclass<AdvEngine> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "adv_engine_goal_seq"
    )
    @SequenceGenerator(name = "adv_engine_goal_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
