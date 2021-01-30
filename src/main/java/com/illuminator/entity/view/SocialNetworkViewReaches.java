package com.illuminator.entity.view;

import com.illuminator.entity.ReachesSuperclass;
import com.illuminator.entity.source.SocialNetwork;

import javax.persistence.*;

@Entity
@Table(
        name = "social_network_views",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date", "counterid"})
)
public class SocialNetworkViewReaches extends ReachesSuperclass<SocialNetwork> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "social_network_views_reaches_seq"
    )
    @SequenceGenerator(name = "social_network_views_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
