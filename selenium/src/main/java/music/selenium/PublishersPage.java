package music.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for Home
 * Author: Ferenc Buzas
 */
public class PublishersPage extends BasePage {
    
    private By contentTitle = By.cssSelector("body > my-app > my-publishers > h2");

    public PublishersPage(WebDriver driver, Header header) {
        this.driver = driver;
        this.header = header;
        
        if ( ! "Publishers".equals(getContentTitle())) {
            throw new IllegalStateException("This is not the Publishers page");
        }
    }

    @Override
    public String getContentTitle() {

        WebElement title = driver.findElement(contentTitle);
        return title.getText();
    }
}
