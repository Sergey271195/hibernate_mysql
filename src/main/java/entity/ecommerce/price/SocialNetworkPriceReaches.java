package entity.ecommerce.price;

import entity.ReachesSuperclass;
import entity.source.AdvEngine;
import entity.source.SocialNetwork;

import javax.persistence.*;

@Entity
@Table(
        name = "social_network_prices",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date", "counterid"})
)
public class SocialNetworkPriceReaches extends ReachesSuperclass<SocialNetwork> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "social_network_price_reaches_seq"
    )
    @SequenceGenerator(name = "social_network_price_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
