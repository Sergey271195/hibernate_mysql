package com.illuminator.entity.ecommerce.price;

import com.illuminator.entity.ReachesSuperclass;
import com.illuminator.entity.source.ReferralSource;

import javax.persistence.*;

@Entity
@Table(
        name = "ref_source_prices",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date", "counterid"})
)
public class ReferralSourcePriceReaches extends ReachesSuperclass<ReferralSource> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ref_source_price_reaches_seq"
    )
    @SequenceGenerator(name = "ref_source_price_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
