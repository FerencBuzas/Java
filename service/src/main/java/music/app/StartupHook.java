package music.app;

import music.dao.DataCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * This is called after the application has been booted.
 * It creates data for the H2 database.
 */
@Component
public class StartupHook implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineRunner.class);

    @Autowired
    private ConfigurableApplicationContext context;

    public void run(String... args) {
        LOGGER.debug("StartupHook.run()");
        context.getBean(DataCreator.class).createData();
    }
}
