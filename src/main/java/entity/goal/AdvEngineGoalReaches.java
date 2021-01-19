package entity.goal;

import entity.source.AdvEngine;
import entity.source.SourceSuperclass;

@Entity
@Table(
        name = "advenginegoal",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"goalid", "advid", "date"})}
)
public class AdvEngineGoalReaches extends GoalReachesSuperclass {

    private long id;
    private AdvEngine advEngine;

    @Column(name = "advid")
    public AdvEngine getAdvEngine() { return advEngine; }

    public void setAdvEngine(AdvEngine advEngine) { this.advEngine = advEngine; }

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            name = "adv_engine_goal_seq"
    )
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

}
