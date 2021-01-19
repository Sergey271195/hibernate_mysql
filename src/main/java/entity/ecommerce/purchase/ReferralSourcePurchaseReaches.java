package entity.ecommerce.purchase;

import entity.ReachesSuperclass;
import entity.source.AdvEngine;
import entity.source.ReferralSource;

import javax.persistence.*;

@Entity
@Table(
        name = "ref_source_purchases",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date"})
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
