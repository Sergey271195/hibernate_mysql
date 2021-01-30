package com.illuminator.entity.source;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "ref_source")
public class ReferralSource extends SourceSuperclass<Integer> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ref_source_seq"
    )
    @SequenceGenerator(name = "ref_source_seq")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Override
    public ReferralSource createSourceFromMetrikData(Map<String, String> sourceData) {
        return SourceSuperclass.createSource(sourceData, this);
    }
}
