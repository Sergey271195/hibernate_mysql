package entity.source;

import javax.persistence.*;

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


}
