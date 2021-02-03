package com.illuminator.db.entity.ecommerce.price;

import com.illuminator.db.entity.ReachesSuperclass;
import com.illuminator.db.entity.source.SearchEngine;

import javax.persistence.*;

@Entity
@Table(
        name = "search_engine_prices",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date", "counterid"})
)
public class SearchEnginePriceReaches extends ReachesSuperclass<SearchEngine> {

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
