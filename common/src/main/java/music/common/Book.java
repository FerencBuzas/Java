package music.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * Actually this is 'sheet music', but 'book' is simpler.
 *
 * Created by Ferenc_Buzas on 13 Sep 2017
 */
@Entity
public class Book implements MusicObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(Book.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @ManyToOne
    private Composer composer;

    @ManyToOne
    private Publisher publisher;

    private int pubYear;

    public Book() {
        LOGGER.debug("Book()");
    }

    public Book(long id, String title, Composer composer, Publisher publisher, int pubYear) {
        LOGGER.debug("Book() id={} title={} composer={}", id, title, composer.getName());

        this.id = id;
        this.title = title;
        this.composer = composer;
        this.publisher = publisher;
        this.pubYear = pubYear;
    }
    
    public Book(String title, Composer composer, Publisher publisher, int pubYear) {
        this(0, title, composer, publisher, pubYear);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Composer getComposer() {
        return composer;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public int getPubYear() {
        return pubYear;
    }
    
    public void modifyDataByOther(Book other) {
        title = other.getTitle();
        composer = other.composer;
        publisher = other.publisher;
        pubYear = other.pubYear;
    }
    
    public String toString() {

        return "Book[id=" +id+ ", title=" +title+ ", "+ composer +
                ", "+ publisher + ", pubYear=" +pubYear+"]";
    }
}
