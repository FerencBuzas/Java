package music.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Publisher implements MusicObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(Composer.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public Publisher() {
        LOGGER.debug("Publisher()");
    }

    public Publisher(long id, String name) {
        LOGGER.debug("Publisher() name={}", name);

        this.id = id;
        this.name = name;
    }

    public Publisher(String name) {
        this(0, name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void modifyDataByOther(Publisher other) {
        name = other.getName();
    }
    
    public String toString() {

        return "Publisher[id=" +id+ ", name=" +name +"]";
    }
}
