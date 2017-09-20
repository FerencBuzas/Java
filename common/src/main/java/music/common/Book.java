package music.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Actually this is 'sheet music', but 'book' is simpler.
 *
 * Created by Ferenc_Buzas on 13 Sep 2017
 */
@Entity
public class Book {

    private static final Logger LOGGER = LoggerFactory.getLogger(Book.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String title;
    Composer composer;
    Publisher publisher;
    int pubYear;

    public Book() {
        LOGGER.debug("## Book()");

        id = 0;
        title = null;
        composer = null;
        publisher = null;
        pubYear = 0;
    }

    public Book(long id, String title, Composer composer, Publisher publisher, int pubYear) {
        LOGGER.debug("## Book() id={}, title={}", id, title);

        this.id = id;
        this.title = title;
        this.composer = composer;
        this.publisher = publisher;
        this.pubYear = pubYear;
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

    public String toString() {

        return "Book[id=" +id+ ", title=" +title+ ", "+ composer +
                ", "+ publisher + ", pubYear=" +pubYear+"]";
    }
}
