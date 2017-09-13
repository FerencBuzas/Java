package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This has a traditional main() method. Uses the Tomcat servlet container in Spring.
 *
 * Created by Ferenc_Buzas on 11/4/2016, from https://spring.io/guides/gs/rest-service/
 */
@SpringBootApplication  // adds: @Configuration, @EnableAutoConfiguration, @EnableWebMvc, @ComponentScan
public class ServiceApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceApp.class);

    public static void main(String[] args) {
        LOGGER.info("## main()");

        SpringApplication.run(ServiceApp.class, args);   // No XML at all!

        LOGGER.info("## end of main()");
    }
}
