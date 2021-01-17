package entity;

import javax.persistence.*;

@Entity
@Table(name = "search_phrase")
public class SearchPhrase extends SourceSuperclass<Integer> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_phrase_seq"
    )
    @SequenceGenerator(name = "search_phrase_seq")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " [id = " + id + toStringInheritedFields();
    }

}
