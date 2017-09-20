package music.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Publisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Composer.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String name;

    public Publisher() {
        LOGGER.debug("## Composer()");

        id = 0;
        name = null;
    }

    public Publisher(long id, String name) {
        LOGGER.debug("## Publisher() id={}, title={}", id, name);

        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {

        return "Composer[id=" +id+ ", title=" +name +"]";
    }

}
