package entity.ecommerce.purchase;

import entity.ReachesSuperclass;
import entity.source.AdvEngine;
import entity.source.SocialNetwork;

import javax.persistence.*;

@Entity
@Table(
        name = "social_network_purchases",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date", "counterid"})
)
public class SocialNetworkPurchaseReaches extends ReachesSuperclass<SocialNetwork> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "social_network_purchase_reaches_seq"
    )
    @SequenceGenerator(name = "social_network_purchase_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
