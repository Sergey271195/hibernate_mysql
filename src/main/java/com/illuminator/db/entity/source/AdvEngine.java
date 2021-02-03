package com.illuminator.db.entity.source;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "adv_engine")
public class AdvEngine extends SourceSuperclass<String> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "adv_engine_seq"
    )
    @SequenceGenerator(name = "adv_engine_seq")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Override
    public AdvEngine createSourceFromMetrikData(Map<String, String> sourceData) {
        return SourceSuperclass.createSource(sourceData, this);
    }
}
