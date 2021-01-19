package entity.goal;

import entity.goal.GoalReachesSuperclass;
import entity.source.AdvEngine;
import entity.source.ReferralSource;
import entity.source.SourceSuperclass;

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
