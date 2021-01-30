package com.illuminator.entity.view;

import com.illuminator.entity.ReachesSuperclass;
import com.illuminator.entity.source.ReferralSource;

import javax.persistence.*;

@Entity
@Table(
        name = "ref_source_views",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date", "counterid"})
)
public class ReferralSourceViewReaches extends ReachesSuperclass<ReferralSource> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ref_source_views_reaches_seq"
    )
    @SequenceGenerator(name = "ref_source_views_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
