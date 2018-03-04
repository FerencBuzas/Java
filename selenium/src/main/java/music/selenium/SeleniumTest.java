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

    private static final String BASE_URL = "localhost:3000";
    private static final int SLEEP_MS = 2500;

    private SelUtil selUtil;

    public SeleniumTest(SelUtil selUtil) {
        this.selUtil = selUtil;
    }

    private void oneRound(WebDriver driver) {
        LOGGER.info("Running test with " + driver);
        if (Objects.isNull(driver)) {
            throw new IllegalStateException("Driver could not be created");
        }
        
        Header header = new Header(driver);
        driver.get(BASE_URL);
        System.out.println("run()- Page title is " + driver.getTitle());
        selUtil.sleep(SLEEP_MS);

        HomePage homePage = header.clickHomeButton();
        selUtil.sleep(SLEEP_MS);

        BooksPage booksPage = header.clickBooksButton();
        selUtil.sleep(SLEEP_MS);

        ComposersPage composersPage = header.clickComposersButton();
        selUtil.sleep(SLEEP_MS);

        PublishersPage publishersPage = header.clickPublishersButton();
        selUtil.sleep(SLEEP_MS);

        header.clickHomeButton();
        selUtil.sleep(SLEEP_MS);

        driver.quit();
    }

    private void run() {

        // If the caller has set some driver property below, that test will be run.
        String chromeProp = System.getProperty("webdriver.chrome.driver");
        String firefoxProp = System.getProperty("webdriver.gecko.driver");
        boolean chromeGiven = Objects.nonNull(chromeProp);
        boolean firefoxGiven = Objects.nonNull(firefoxProp);
        if (chromeGiven || firefoxGiven) {
            if (chromeGiven) {
                oneRound(new ChromeDriver()); 
            }
            if (firefoxGiven) {
                oneRound(new FirefoxDriver());
            }
        }
        else {
            // Try to run a Selenium driver on the path.
            oneRound(new ChromeDriver());
        }
    }
    
    public static void main(String[] args) {
        LOGGER.info("main()");
        new SeleniumTest(new SelUtil()).run();
    }
}
