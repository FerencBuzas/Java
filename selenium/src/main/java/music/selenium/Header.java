package music.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object for the common header block.
 * Author: Ferenc Buzas
 */
public class Header {

    private static final Logger LOGGER = LoggerFactory.getLogger(Header.class);

    By menuTitle = By.xpath("/html/body/my-app/h1");
    By homeButton = By.xpath("/html/body/my-app/nav/a[1]");
    By booksButton = By.xpath("/html/body/my-app/nav/a[2]");
    By composersButton = By.xpath("/html/body/my-app/nav/a[3]");
    By publishersButton = By.xpath("/html/body/my-app/nav/a[4]");

    private WebDriver driver;
    
    public Header(WebDriver driver) {
        this.driver = driver;
    }

    void verifyBlock() {
        
        checkElement(homeButton);
        checkElement(booksButton);
        checkElement(composersButton);
        checkElement(publishersButton);
    }

    private void checkElement(By by) {
        WebElement element = driver.findElement(by);
        if ( ! element.isDisplayed()) {
            throw new IllegalStateException("Element not displayed: " + by);
        }
        if ( ! element.isEnabled()) {
            throw new IllegalStateException("Element not enabled: " + by);
        }
    }

    void clickButton(By by) {
        LOGGER.debug("clickButton() " + by);
        WebElement button = driver.findElement(by);
        button.click();
    }

    HomePage clickHomeButton() {
        LOGGER.debug("clickHomeButton");
        clickButton(homeButton);
        return new HomePage(driver, this); 
    }
    
    BooksPage clickBooksButton() {
        LOGGER.debug("clickBooksButton");
        clickButton(booksButton);
        return new BooksPage(driver, this);
    }

    ComposersPage clickComposersButton() {
        LOGGER.debug("clickComposersButton");
        clickButton(composersButton);
        return new ComposersPage(driver, this);
    }
    
    PublishersPage clickPublishersButton() {
        LOGGER.debug("clickPublishersButton");
        clickButton(publishersButton);
        return new PublishersPage(driver, this);
    }
}
