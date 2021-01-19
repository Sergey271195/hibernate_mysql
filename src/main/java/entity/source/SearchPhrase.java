package entity.source;

import javax.persistence.*;

@Entity
@Table(name = "search_phrase")
@AttributeOverride(name="name", column = @Column(name="name", length = 500))
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


}
