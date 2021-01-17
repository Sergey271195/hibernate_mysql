package entity;

import javax.persistence.*;

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
    public String toString() {
        return this.getClass().getSimpleName() +
                " [id = " + id + toStringInheritedFields();
    }

}
