package entity.ecommerce.price;

import entity.ReachesSuperclass;
import entity.source.AdvEngine;
import entity.source.ReferralSource;

import javax.persistence.*;

@Entity
@Table(
        name = "ref_source_prices",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date"})
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
