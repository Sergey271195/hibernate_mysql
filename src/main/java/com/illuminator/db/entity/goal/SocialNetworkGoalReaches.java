package com.illuminator.db.entity.goal;

import com.illuminator.db.entity.source.SocialNetwork;

import javax.persistence.*;

@Entity
@Table(
        name = "soc_network_goal",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"goalid", "sourceid", "date"})}
)
public class SocialNetworkGoalReaches extends GoalReachesSuperclass<SocialNetwork> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "social_network_goal_seq"
    )
    @SequenceGenerator(name = "social_network_goal_seq")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

}
