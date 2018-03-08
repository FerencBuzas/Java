package music.selenium;

import music.util.SelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for Home
 * Author: Ferenc Buzas
 */
public class HomePage extends BasePage {

    private static By contentTitle = By.cssSelector("body > my-app > my-home > div > h2");

    public HomePage(WebDriver driver, SelUtil selUtil, Header header) {
        super(driver, selUtil, header, contentTitle, HomePage.class);

        if ( ! "Main page".equals(getContentTitle())) {
            throw new IllegalStateException("This is not the Main page");
        }
    }
    
//    public boolean verifySignIn() {
//        enterUserName("test");
//        clickOnSignIn();
//        return getErrorMessage().contains("incorrect");
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
