package music.app;

import music.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = "music.common, music.dao")
public class AppConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);
	
	@Bean
	@Profile({"dev", "default"})
    public BookDao bookDaoDev() {
		LOGGER.info("bookDaoDev() ##");
        return new BookDaoJpa();
    }

	@Bean
	@Profile("test")
	public BookDao bookDaoTest() {
		LOGGER.info("bookDaoTest() ##");
        ComposerDataCreator cDC = new ComposerDataCreator(2);
        ComposerDaoMemory cDM = new ComposerDaoMemory(cDC);
		PublisherDataCreator pDC = new PublisherDataCreator();
		PublisherDaoMemory pDM = new PublisherDaoMemory(pDC);
		BookDataCreator bDC = new BookDataCreator(cDC, pDC);
		return new BookDaoMemory(cDM, pDM, bDC);
	}
	
    @Bean
    @Profile({"dev", "default"})
    public ComposerDao composerDaoDev() {
        LOGGER.info("composerDaoDev() ##");
        return new ComposerDaoJpa();
    }

    @Bean
    @Profile("test")
    public ComposerDao composerDaoTest() {
        LOGGER.info("composerDaoTest() ##");
        return new ComposerDaoMemory(new ComposerDataCreator(2));
    }

    @Bean
    @Profile({"dev", "default"})
    public PublisherDao publisherDaoDev() {
        LOGGER.info("publisherDaoDev() ##");
        return new PublisherDaoJpa();
    }

    @Bean
    @Profile("test")
    public PublisherDao publisherDaoTest(@Autowired PublisherDataCreator publisherDataCreator) {
        LOGGER.info("publisherDaoTest() ##");
        return new PublisherDaoMemory(publisherDataCreator);
    }
}
