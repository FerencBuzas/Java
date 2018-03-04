package music.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for Books
 * Author: Ferenc Buzas
 */
public class BooksPage extends BasePage {
    
    private static By contentTitle = By.cssSelector("body > my-app > my-books > h2");

    public BooksPage(WebDriver driver, Header header) {
        super(driver, header, contentTitle, BooksPage.class);

        if ( ! "Books".equals(getContentTitle())) {
            throw new IllegalStateException("This is not the Books page");
        }
    }

//    public void enterUserName(String userName) {
//        WebElement emailTxtBox = driver.findElement(emailTextBox);
//        if(emailTxtBox.isDisplayed())
//            emailTxtBox.sendKeys(userName);
//    }
//
//    public void clickOnSignIn() {
//        WebElement signInBtn = driver.findElement(loginBtn);
//        if(signInBtn.isDisplayed())
//            signInBtn.click();
//    }
    
}
