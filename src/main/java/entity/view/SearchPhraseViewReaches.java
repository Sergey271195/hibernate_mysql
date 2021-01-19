package entity.view;

import entity.ReachesSuperclass;
import entity.source.SearchPhrase;

import javax.persistence.*;

@Entity
@Table(
        name = "search_phrase_views",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date"})
)
public class SearchPhraseViewReaches extends ReachesSuperclass<SearchPhrase> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_phrase_views_reaches_seq"
    )
    @SequenceGenerator(name = "search_phrase_views_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
