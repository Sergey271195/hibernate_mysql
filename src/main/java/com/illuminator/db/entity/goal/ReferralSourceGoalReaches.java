package com.illuminator.db.entity.goal;

import com.illuminator.db.entity.source.ReferralSource;

import javax.persistence.*;

@Entity
@Table(
        name = "ref_source_goal",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"goalid", "sourceid", "date"})}
)
public class ReferralSourceGoalReaches extends GoalReachesSuperclass<ReferralSource> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ref_source_goal_seq"
    )
    @SequenceGenerator(name = "ref_source_goal_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
}
