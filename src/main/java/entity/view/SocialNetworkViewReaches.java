package entity.view;

import entity.ReachesSuperclass;
import entity.source.SocialNetwork;

import javax.persistence.*;

@Entity
@Table(
        name = "social_network_views",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date"})
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
