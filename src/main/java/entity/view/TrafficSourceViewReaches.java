package entity.view;

import entity.ReachesSuperclass;
import entity.source.TrafficSource;

import javax.persistence.*;

@Entity
@Table(
        name = "traff_source_views",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sourceid", "date"})
)
public class TrafficSourceViewReaches extends ReachesSuperclass<TrafficSource> {

    private long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "traff_source_views_reaches_seq"
    )
    @SequenceGenerator(name = "traff_source_views_reaches_seq")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

}
