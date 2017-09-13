package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * The basic controller, coming from the example (see also: PersonController).
 * Created by Ferenc_Buzas on 11/4/2016.
 */

@RestController   // New annotation from Spring 4: marks that every method returns a domain object, not a view
                  // It’s shorthand for @Controller and @ResponseBody rolled together.
public class GreetingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    private final AtomicLong counter = new AtomicLong();

    /**
     * @RequestMapping: maps HTTP requests to "/greeting" to this greeting() method. (all: GET,POST,..)
     * @RequestParam: binds query param "name" to the 'name' param of the greeting() method.
     */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        LOGGER.info("## greeting() name={}", name);

        return new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
    }
}
