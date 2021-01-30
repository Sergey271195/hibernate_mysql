package com.illuminator.entity.ecommerce.purchase;

import com.illuminator.entity.ReachesSuperclass;
import com.illuminator.entity.source.ReferralSource;

import javax.persistence.*;

@Entity
@Table(
        name = "ref_source_purchases",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date", "counterid"})
)
public class ReferralSourcePurchaseReaches extends ReachesSuperclass<ReferralSource> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ref_source_purchase_reaches_seq"
    )
    @SequenceGenerator(name = "ref_source_purchase_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
