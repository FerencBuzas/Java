package music.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for Composers
 * Author: Ferenc Buzas
 */
public class ComposersPage extends BasePage {
    
    private By contentTitle = By.cssSelector("body > my-app > my-composers > h2");

    public ComposersPage(WebDriver driver, Header header) {
        this.driver = driver;
        this.header = header;
        
        if ( ! "Composers".equals(getContentTitle())) {
            throw new IllegalStateException("This is not the Composers page");
        }
    }

    @Override
    public String getContentTitle() {

        WebElement title = driver.findElement(contentTitle);
        return title.getText();
    }
}
