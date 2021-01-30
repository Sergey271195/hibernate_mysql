package com.illuminator.entity.source;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "soc_network")
public class SocialNetwork extends SourceSuperclass<String> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "soc_network_seq"
    )
    @SequenceGenerator(name = "soc_network_seq")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Override
    public SocialNetwork createSourceFromMetrikData(Map<String, String> sourceData) {
        return SourceSuperclass.createSource(sourceData, this);
    }
}
