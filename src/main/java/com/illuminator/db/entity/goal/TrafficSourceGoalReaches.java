package com.illuminator.db.entity.goal;

import com.illuminator.db.entity.source.TrafficSource;

import javax.persistence.*;

@Entity
@Table(
        name = "traff_source_goal",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"goalid", "sourceid", "date"})}
)
public class TrafficSourceGoalReaches extends GoalReachesSuperclass<TrafficSource> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "traffic_source_goal_seq"
    )
    @SequenceGenerator(name = "traffic_source_goal_seq")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

}
