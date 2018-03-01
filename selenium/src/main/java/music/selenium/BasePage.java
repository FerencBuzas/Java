package music.selenium;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected Header    header;

    public abstract String getContentTitle();
}
