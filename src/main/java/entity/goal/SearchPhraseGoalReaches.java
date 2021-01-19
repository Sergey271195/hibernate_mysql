package entity.goal;

import entity.goal.GoalReachesSuperclass;
import entity.source.SearchPhrase;
import entity.source.SourceSuperclass;

import javax.persistence.*;

@Entity
@Table(
        name = "search_phrase_goal",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"goalid", "sourceid", "date"})}
)
public class SearchPhraseGoalReaches extends GoalReachesSuperclass<SearchPhrase> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_phrase_goal_seq"
    )
    @SequenceGenerator(name = "search_phrase_goal_seq")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

}
