package entity.ecommerce.price;

import entity.ReachesSuperclass;
import entity.source.AdvEngine;
import entity.source.TrafficSource;

import javax.persistence.*;

@Entity
@Table(
        name = "traff_source_prices",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date", "counterid"})
)
public class TrafficSourcePriceReaches extends ReachesSuperclass<TrafficSource> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "traff_source_price_reaches_seq"
    )
    @SequenceGenerator(name = "traff_source_price_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
