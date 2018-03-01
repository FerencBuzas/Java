package music.selenium;

import music.util.SelUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
    
    private static final String CHROME_DRIVER = "/home/buzas/programs/selenium/chromedriver";
    private static final String FIREFOX_DRIVER = "/home/buzas/programs/selenium/geckodriver";
    
    private static final String BASE_URL = "localhost:3000";
    private static final int SLEEP_MS = 2500;

    enum Driver { FIREFOX, CHROME }
    
    private SelUtil selUtil;
    private Header header;
    private WebDriver driver;

    public SeleniumTest(SelUtil selUtil) {
        this.selUtil = selUtil;
    }
    
    private void run() {

        driver = createDriver(Driver.CHROME);
        header = new Header(driver);
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
    
    public WebDriver createDriver(Driver driver) {
        
        switch (driver) {
            
            case CHROME:    // NOTE: use driver version 2.28
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
                return new ChromeDriver();
                
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER);
                return new FirefoxDriver();

            default:
                throw new IllegalArgumentException("Bad driver: " + driver);
        }
    }

    public static void main(String[] args) {

        new SeleniumTest(new SelUtil()).run();
    }
}
