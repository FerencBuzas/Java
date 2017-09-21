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
    private long id;

    private String name;

    public Publisher() {
        LOGGER.debug("Publisher()");
    }

    public Publisher(String name) {
        LOGGER.debug("Publisher() name={}", name);

        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {

        return "Publisher[id=" +id+ ", name=" +name +"]";
    }
}
