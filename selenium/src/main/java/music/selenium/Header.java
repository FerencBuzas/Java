package music.selenium;

import music.util.SelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for the common header block.
 * Author: Ferenc Buzas
 */
public class Header extends BasePage {
    
    By menuTitle = By.xpath("/html/body/my-app/h1");
    By homeButton = By.xpath("/html/body/my-app/nav/a[1]");
    By booksButton = By.xpath("/html/body/my-app/nav/a[2]");
    By composersButton = By.xpath("/html/body/my-app/nav/a[3]");
    By publishersButton = By.xpath("/html/body/my-app/nav/a[4]");

    private final WebDriver driver;
    protected final SelUtil selUtil;

    public Header(WebDriver driver, SelUtil selUtil) {
        super(driver, selUtil, null, null, Header.class);
        this.driver = driver;
        this.selUtil = selUtil;
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

    HomePage clickHomeButton() {
        clickButton(homeButton);
        return new HomePage(driver, selUtil, this); 
    }
    
    BooksPage clickBooksButton() {
        clickButton(booksButton);
        return new BooksPage(driver, selUtil, this);
    }

    ComposersPage clickComposersButton() {
        clickButton(composersButton);
        return new ComposersPage(driver, selUtil, this);
    }
    
    PublishersPage clickPublishersButton() {
        clickButton(publishersButton);
        return new PublishersPage(driver, selUtil, this);
    }
}
