package music.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for Composers
 * Author: Ferenc Buzas
 */
public class ComposersPage extends BasePage {
    
    private static By contentTitle = By.cssSelector("body > my-app > my-composers > h2");

    public ComposersPage(WebDriver driver, Header header) {
        super(driver, header, contentTitle, ComposersPage.class);
        
        if ( ! "Composers".equals(getContentTitle())) {
            throw new IllegalStateException("This is not the Composers page");
        }
    }
}
