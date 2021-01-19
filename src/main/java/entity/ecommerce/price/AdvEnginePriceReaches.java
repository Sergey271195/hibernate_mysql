package entity.ecommerce.price;

import entity.ReachesSuperclass;
import entity.source.AdvEngine;

import javax.persistence.*;

@Entity
@Table(
        name = "adv_engine_prices",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date"})
)
public class AdvEnginePriceReaches extends ReachesSuperclass<AdvEngine> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "adv_engine_price_reaches_seq"
    )
    @SequenceGenerator(name = "adv_engine_price_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
