package music.selenium;

import music.util.SelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class BasePage {
    
    protected Logger LOGGER;
    
    protected WebDriver driver;
    private final SelUtil selUtil;
    protected Header    header;
    protected final By contentTitle;

    public BasePage(WebDriver driver, SelUtil selUtil, Header header, By contentTitle, Class clazz) {

        this.driver = driver;
        this.selUtil = selUtil;
        this.header = header;
        this.contentTitle = contentTitle;
        this.LOGGER = LoggerFactory.getLogger(clazz);
    }
        
    protected String getContentTitle() {
        WebElement title = driver.findElement(contentTitle);
        return title.getText();
    }

    WebElement findElementWithWait(By by) {
        
        WebElement result;
        int maxTries = 1500 / 200;
        for (int i = 0; i < maxTries; ++i) {
            try {
                result = driver.findElement(by);
                return result;
            }
            catch (NoSuchElementException e) {
                selUtil.sleep(200);
            }
        }
        return null;
    }
    
    protected WebElement checkVisible(By by) {
        WebElement element = findElementWithWait(by);
        if (Objects.isNull(element)  ||  ! element.isDisplayed()) {
            throw new IllegalStateException("Element is not displayed: " + by);
        }
        return element;
    }

    protected WebElement checkEnabled(By by) {
        WebElement element = checkVisible(by);
        if (Objects.isNull(element)  ||  ! element.isDisplayed()) {
            throw new IllegalStateException("Element is not enabled: " + by);
        }
        return element;
    }

    void clickButton(By by) {
        LOGGER.debug("clickButton() " + by);
        WebElement button = findElementWithWait(by);
        button.click();
    }

    void enterText(By by, String text) {
        WebElement element = checkEnabled(by);
        element.sendKeys(text + "\n");
    }

    void select(By by, String text) {
        WebElement element = checkEnabled(by);
        element.click();
        LOGGER.debug("select(): clicked");
        element.sendKeys(text + "\n");
    }


    void myAssert(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
