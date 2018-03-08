package music.selenium;

import music.util.SelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for Books
 * Author: Ferenc Buzas
 */
public class BooksPage extends BasePage {
    
    private static By contentTitle = By.cssSelector("body > my-app > my-books > h2");
    private static By BachWTC2 = By.cssSelector("body > my-app > my-books > ul > li:nth-child(3)");
    
    private static By newBookButton = By.cssSelector("body > my-app > my-books > button");

    // --- details ---
    
    private static By detailTitle = By.cssSelector(
            "body > my-app > my-book-detail > div > div:nth-child(3) > input");

    private static By detailComposer = By.cssSelector(
            "body > my-app > my-book-detail > div > div:nth-child(4) > select");
    
    private static By detailPublisher = By.cssSelector(
            "body > my-app > my-book-detail > div > div:nth-child(5) > select");

    private static By detailPubYear = By.cssSelector(
            "body > my-app > my-book-detail > div > div:nth-child(6) > input");

    private static By detailBackButton = By.cssSelector(
        "body > my-app > my-book-detail > div > button:nth-child(7)");

    // --- New book ---

    private static By newTitle = By.cssSelector(
            "body > my-app > my-book-detail > div > div:nth-child(2) > input");
    
    private static By newComposer = By.cssSelector(
            "body > my-app > my-book-detail > div > div:nth-child(3) > select");

    private static By newPublisher = By.cssSelector(
            "body > my-app > my-book-detail > div > div:nth-child(4) > select");

    private static By newPubYear = By.cssSelector(
            "body > my-app > my-book-detail > div > div:nth-child(5) > input");

    private static By newBackButton = By.cssSelector(
            "body > my-app > my-book-detail > div > button:nth-child(6)");

    
    // --- methods ---

    public BooksPage(WebDriver driver, SelUtil selUtil, Header header) {
        super(driver, selUtil, header, contentTitle, BooksPage.class);
        
        if ( ! "Books".equals(getContentTitle())) {
            throw new IllegalStateException("This is not the Books page");
        }
    }

    void validateMainPage() {

        checkVisible(contentTitle);
        checkVisible(BachWTC2);
    }
    
    void validateDetailsPage() {

        checkVisible(detailTitle);
        checkVisible(detailComposer);
        checkVisible(detailPublisher);
        checkVisible(detailPubYear);
    }

    void validateNewPage() {

        checkVisible(newTitle);
        checkVisible(newComposer);
        checkVisible(newPublisher);
        checkVisible(newPubYear);
    }

    public void enterTitle(String title) {
        myAssert(title != null && ! title.isEmpty(), "Title must not be empty");
        enterText(newTitle, title);
    }

    public void selectComposer(String name) {
        super.select(newComposer, name);
    }
    
    public void selectPublisher(String name) {
        super.select(newPublisher, name);
    }

    public void enterPubYear(int year) {
        myAssert(1500 <= year && year < 2051, "Bad year number " + year);
        enterText(newPubYear, Integer.toString(year));
    }

    public void clickOnWTC2() {
        WebElement wtc2 = checkEnabled(BachWTC2);
        wtc2.click();
    }

    public void clickOnBack() {
        WebElement back = checkEnabled(detailBackButton);
        back.click();
    }

    public void clickOnNewBack() {
        WebElement back = checkEnabled(newBackButton);
        back.click();
    }

    public void clickOnNewBook() {
        WebElement newButton = checkEnabled(newBookButton);
        newButton.click();
    }
}
