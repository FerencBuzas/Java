package music.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "music.common, music.dao")
public class AppConfig {
/**
	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);
	
	@Bean
    @Profile({"dev", "default"})
    public BookDao bookDaoDev() {
		LOGGER.info("bookDaoDev() ##");
        return new BookDaoJpa();
    }

    @Bean
    @Profile({"dev", "default"})
    public ComposerDao composerDaoDev() {
        LOGGER.info("composerDaoDev() ##");
        return new ComposerDaoJpa();
    }

    @Bean
    @Profile({"dev", "default"})
    public PublisherDao publisherDaoDev() {
        LOGGER.info("publisherDaoDev() ##");
        return new PublisherDaoJpa();
    }

    // ======== test ======================
    
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
    @Profile("test")
    public ComposerDao composerDaoTest() {
        LOGGER.info("composerDaoTest() ##");
        return new ComposerDaoMemory(new ComposerDataCreator(2));
    }

    @Bean
    @Profile("test")
    public PublisherDao publisherDaoTest(@Autowired PublisherDataCreator publisherDataCreator) {
        LOGGER.info("publisherDaoTest() ##");
        return new PublisherDaoMemory(publisherDataCreator);
    }
**/
}
