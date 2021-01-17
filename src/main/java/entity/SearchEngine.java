package entity;

import javax.persistence.*;

@Entity
@Table(name = "search_engine")
public class SearchEngine extends SourceSuperclass<String> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_engine_seq"
    )
    @SequenceGenerator(name = "search_engine_seq")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " [id = " + id + toStringInheritedFields();
    }

}
