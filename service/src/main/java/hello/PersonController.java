package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ferenc_Buzas on 11/4/2016.
 */

@CrossOrigin()   // Necessary to use it from another domain
@RestController
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @RequestMapping("/person")
    public Person person(@RequestParam(value="id") String id) {
        LOGGER.info("## person() id={}", id);

        return new Person(id);
    }
}
