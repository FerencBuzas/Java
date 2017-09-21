package music.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This has a traditional main() method. Uses the Tomcat servlet container in Spring.
 */
@SpringBootApplication
public class MusicServiceApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicServiceApp.class);

    public static void main(String[] args) {
        LOGGER.info("main()");

        SpringApplication.run(MusicServiceApp.class, args);

        // Setup here works only with embedded Tomcat, not from a War. Hence StartupHook.

        LOGGER.info("end of main()");
    }
}
