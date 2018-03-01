package music.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for Books
 * Author: Ferenc Buzas
 */
public class BooksPage extends BasePage {
    
    private By contentTitle = By.cssSelector("body > my-app > my-books > h2");

    public BooksPage(WebDriver driver, Header header) {
        this.driver = driver;
        this.header = header;
        
        if ( ! "Books".equals(getContentTitle())) {
            throw new IllegalStateException("This is not the Books page");
        }
    }

    @Override
    public String getContentTitle() {

        WebElement title = driver.findElement(contentTitle);
        return title.getText();
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
