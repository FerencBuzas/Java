package music.selenium;

import music.util.SelUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class SeleniumTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumTest.class);

    private Config config;
    private SelUtil selUtil;
    
    public SeleniumTest(Config config, SelUtil selUtil) {
        this.config = config;
        this.selUtil = selUtil;
    }

    private void oneRound(WebDriver driver, boolean detailed) {
        LOGGER.info("Running test with " + driver);
        if (Objects.isNull(driver)) {
            throw new IllegalStateException("Driver could not be created");
        }
        
        Header header = new Header(driver, selUtil);
        driver.get(config.getBaseUrl());
        System.out.println("run()- Page title is " + driver.getTitle());
        selUtil.sleepShort();

        HomePage homePage = header.clickHomeButton();
        selUtil.sleepShort();

        BooksPage booksPage = header.clickBooksButton();
        selUtil.sleepShort();
        if (detailed) {
            new BooksTest(config, selUtil, booksPage).test();
            selUtil.sleepLong();
        }

        ComposersPage composersPage = header.clickComposersButton();
        selUtil.sleepShort();

        PublishersPage publishersPage = header.clickPublishersButton();
        selUtil.sleepShort();

        header.clickHomeButton();
        selUtil.sleepShort();

        selUtil.sleepLong();
        driver.quit();
    }

    private void run(boolean detailed) {

        // If the caller has set some driver property below, that test will be run.
        String chromeDriverPath = System.getProperty(config.getChromeDriverPropName());
        String firefoxDriverPath = System.getProperty(config.getFirefoxDriverPropName());
        boolean chromeGiven = Objects.nonNull(chromeDriverPath);
        boolean firefoxGiven = Objects.nonNull(firefoxDriverPath);
        
        if (chromeGiven || firefoxGiven) {
            // The caller has set one or more drivers
            if (chromeGiven) {
                oneRound(new ChromeDriver(), detailed); 
            }
            if (firefoxGiven) {
                oneRound(new FirefoxDriver(), detailed);
            }
        }
        else {
            // Try to find a Selenium driver on the path.
            oneRound(new ChromeDriver(), detailed);
        }
    }
    
    public static void main(String[] args) {
        LOGGER.info("main()");
        
        // My own dependency injection
        Config config = new Config();
        SelUtil selUtil = new SelUtil(config);
        
        new SeleniumTest(config, selUtil).run(true);
    }
}
