package com.illuminator.db.entity.ecommerce.purchase;

import com.illuminator.db.entity.ReachesSuperclass;
import com.illuminator.db.entity.source.SocialNetwork;

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
