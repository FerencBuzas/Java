package music.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for Home
 * Author: Ferenc Buzas
 */
public class PublishersPage extends BasePage {
    
    private static By contentTitle = By.cssSelector("body > my-app > my-publishers > h2");

    public PublishersPage(WebDriver driver, Header header) {
        super(driver, header, contentTitle, PublishersPage.class);
        
        if ( ! "Publishers".equals(getContentTitle())) {
            throw new IllegalStateException("This is not the Publishers page");
        }
    }
}
