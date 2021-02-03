package com.illuminator.db.entity.view;

import com.illuminator.db.entity.ReachesSuperclass;
import com.illuminator.db.entity.source.AdvEngine;

import javax.persistence.*;

@Entity
@Table(
        name = "adv_engine_views",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date", "counterid"})
)
public class AdvEngineViewReaches extends ReachesSuperclass<AdvEngine> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "adv_engine_views_reaches_seq"
    )
    @SequenceGenerator(name = "adv_engine_views_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
}