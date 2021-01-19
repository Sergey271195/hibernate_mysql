package entity.goal;

import entity.goal.GoalReachesSuperclass;
import entity.source.SearchEngine;
import entity.source.SourceSuperclass;

import javax.persistence.*;

@Entity
@Table(
        name = "search_engine_goal",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"goalid", "sourceid", "date"})}
)
public class SearchEngineGoalReaches extends GoalReachesSuperclass<SearchEngine> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_engine_goal_seq"
    )
    @SequenceGenerator(name = "search_engine_goal_seq")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }
}
