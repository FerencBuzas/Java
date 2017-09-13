package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A bean with an id and a string.
 *
 * Created by Ferenc_Buzas on 11/4/2016.
 */
public class Greeting {

    private static final Logger LOGGER = LoggerFactory.getLogger(Greeting.class);

    public long id;
    public String content;

    public Greeting() {
        LOGGER.debug("## Greeting()");

        this.id = 0;
        this.content = null;
    }

    public Greeting(long id, String content) {
        LOGGER.debug("## Greeting() id={}, content={}", id, content);

        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        LOGGER.debug("## toString()");

        return content;
    }
}
