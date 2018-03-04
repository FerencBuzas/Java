package music.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    
    protected Logger LOGGER;
    
    protected WebDriver driver;
    protected Header    header;
    protected final By contentTitle;

    public BasePage(WebDriver driver, Header header, By contentTitle, Class clazz) {

        this.driver = driver;
        this.header = header;
        this.contentTitle = contentTitle;
        this.LOGGER = LoggerFactory.getLogger(clazz);
    }
        
    protected String getContentTitle() {
        WebElement title = driver.findElement(contentTitle);
        return title.getText();
    }

    void clickButton(By by) {
        LOGGER.debug("clickButton() " + by);
        WebElement button = driver.findElement(by);
        button.click();
    }
}
