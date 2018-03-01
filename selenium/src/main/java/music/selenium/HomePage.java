package music.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for Home
 * Author: Ferenc Buzas
 */
public class HomePage extends BasePage {

    private By contentTitle = By.cssSelector("body > my-app > my-home > div > h2");

    public HomePage(WebDriver driver, Header header) {
        this.driver = driver;
        this.header = header;
        
        if ( ! "Main page".equals(getContentTitle())) {
            throw new IllegalStateException("This is not the Main page");
        }
    }

    @Override
    public String getContentTitle() {
        WebElement title = driver.findElement(contentTitle);
        return title.getText();
    }

//    public boolean verifySignInPageText() {
//        WebElement element = driver.findElement(headerPageText);
//        String pageText = element.getText();
//        String expectedPageText = "Sign in with your Google Account";
//        return pageText.contains(expectedPageText);
//    }

//    public boolean verifySignIn() {
//        enterUserName("test");
//        clickOnSignIn();
//        return getErrorMessage().contains("incorrect");
//    }
//
//    public void enterUserName(String userName) {
//        WebElement emailTxtBox = driver.findElement(emailTextBox);
//        if(emailTxtBox.isDisplayed())
//            emailTxtBox.sendKeys(userName);
//    }
//
//    public String getErrorMessage() {
//        String strErrorMsg = null;
//        WebElement errorMsg = driver.findElement(errorMsgTxt);
//        if(errorMsg.isDisplayed()&&errorMsg.isEnabled())
//            strErrorMsg = errorMsg.getText();
//        return strErrorMsg;
//    }

}
