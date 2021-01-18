package entity.source;

import javax.persistence.*;

@Entity
@Table(name = "traff_source")
public class TrafficSource extends SourceSuperclass<String> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "traff_source_seq"
    )
    @SequenceGenerator(name = "traff_source_seq")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " [id = " + id + toStringInheritedFields();
    }

}
