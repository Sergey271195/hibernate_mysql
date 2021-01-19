package entity.ecommerce.price;

import entity.ReachesSuperclass;
import entity.source.AdvEngine;
import entity.source.SearchPhrase;

import javax.persistence.*;

@Entity
@Table(
        name = "search_phrase_prices",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date"})
)
public class SearchPhrasePriceReaches extends ReachesSuperclass<SearchPhrase> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_phrase_price_reaches_seq"
    )
    @SequenceGenerator(name = "search_phrase_price_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
