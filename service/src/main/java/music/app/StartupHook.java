package music.app;

import music.dao.DataCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * This is called after the application has been booted.
 * It creates data for the H2 database.
 */
@Component
public class StartupHook implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineRunner.class);

    private final ConfigurableApplicationContext context;
    private final Environment environment;
    
    @Autowired
    public StartupHook(ConfigurableApplicationContext context,
                       Environment environment) {
        this.context = context;
        this.environment = environment;
    }

    public void run(String... args) {
        LOGGER.info("StartupHook.run() ##");
        
        boolean test = false;
        for (String s : environment.getActiveProfiles()) {
            if (s.equals("test")) {
                LOGGER.info("StartupHook: test=true");
                test = true;
            }
        }
        context.getBean(DataCreator.class).createData(test ? 2 : 99, !test);
    }
}
