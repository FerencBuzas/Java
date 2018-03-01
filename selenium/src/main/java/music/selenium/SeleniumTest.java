package music.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
    
    private static final String CHROME_DRIVER = "/home/buzas/programs/selenium/chromedriver";
    private static final String FIREFOX_DRIVER = "/home/buzas/programs/selenium/geckodriver";
    
    private static final String BASE_URL = "localhost:3000/books";
//    private static final String G_URL = "https://www.google.com";

    enum Driver { FIREFOX, CHROME }
    
    private void testSheetMusicRegister() {

        WebDriver driver = createDriver(Driver.CHROME);
        
        driver.get(BASE_URL);
        System.out.println("##### Page Title is " + driver.getTitle());
        sleep(5000);
    
        driver.quit();
    }
    
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Sleep " + millis + " interrupted.");
        }
    }  
    
    private WebDriver createDriver(Driver driver) {
        
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
        new SeleniumTest().testSheetMusicRegister();
    }
}
