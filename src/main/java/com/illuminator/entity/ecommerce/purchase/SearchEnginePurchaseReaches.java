package com.illuminator.entity.ecommerce.purchase;

import com.illuminator.entity.ReachesSuperclass;
import com.illuminator.entity.source.SearchEngine;

import javax.persistence.*;

@Entity
@Table(
        name = "search_engine_purchases",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date", "counterid"})
)
public class SearchEnginePurchaseReaches extends ReachesSuperclass<SearchEngine> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_engine_purchase_reaches_seq"
    )
    @SequenceGenerator(name = "search_engine_purchase_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
