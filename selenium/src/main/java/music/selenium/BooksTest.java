package music.selenium;

import music.util.SelUtil;

public class BooksTest {

    private final Config config;
    private final SelUtil selUtil;
    private final BooksPage booksPage;
    
    BooksTest(Config config, SelUtil selUtil, BooksPage booksPage){
        this.config = config;
        this.selUtil = selUtil;
        this.booksPage = booksPage;
    }
    
    void test() {
        selUtil.sleepShort();
        booksPage.validateMainPage();
        
        // Select Bach WTC 2
        booksPage.clickOnWTC2();
        selUtil.sleepShort();
        
        // Validate the details fields
        booksPage.validateDetailsPage();
        
        // Back
        booksPage.clickOnBack();
        selUtil.sleepShort();
        booksPage.validateMainPage();
        
        // [ New ]
        booksPage.clickOnNewBook();
        selUtil.sleepShort();
        booksPage.validateNewPage();
        
        booksPage.enterTitle("testBook1");
        booksPage.selectComposer("Bach");
        booksPage.selectPublisher("Breitkopf");
        booksPage.enterPubYear(1804);
        selUtil.sleepLong();
        
        // Back
        booksPage.clickOnNewBack();
        selUtil.sleepShort();
        booksPage.validateMainPage();
    }
}
