package music.app;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import music.dao.BookDao;
import music.dao.ComposerDao;
import music.dao.PublisherDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * From: https://spring.io/guides/tutorials/bookmarks/
 * 
 * @Author  Ferenc Buzas
 */

@RestController
@CrossOrigin     // Adds Allow Cross Origin to header; TODO: restrict to address ...
@ComponentScan(basePackages = "music.common, music.dao")
public class MusicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicController.class);

    private static final String URL_BASE_FOR_LINKS = "localhost:8080/music";

    private BookDao bookDao;
    private ComposerDao composerDao;
    private PublisherDao publisherDao;

    @Autowired
    MusicController(BookDao bookDao, ComposerDao composerDao, PublisherDao publisherDao) {

        this.bookDao = bookDao;
        this.composerDao = composerDao;
        this.publisherDao = publisherDao;
    }
    
    @RequestMapping("/music")
    public String music() {
        // Create a HTML page with links to the 3 lists, to use just from a browser.
        return link("book") + link("composer") + link("publisher");
    }

    private static String link(String lastWord) {
        return String.format("<a href=\"%s/%s\">%s</a><br>", URL_BASE_FOR_LINKS, lastWord, lastWord);
    }

    // ======== Book ================================
    
    @RequestMapping(method=RequestMethod.GET, value="/music/book")
    public Collection<Book> getBookList() {
        return bookDao.getBooks();
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/music/book/{id}")
    public String removeBook(@PathVariable String id) {

        LOGGER.info("removeBook id={}", id);
        
        bookDao.deleteBook(Long.parseLong(id));
        return "{ \"status\": \"OK\" }";
    }

    @RequestMapping(method=RequestMethod.POST, value="/music/book")
    public String storeBook(
            @RequestBody Book input) {
        
        LOGGER.info("storeBook input={}", input);
        
        bookDao.storeBook(input);
        return "{ \"status\": \"OK\" }";
    }

    // ======== Composer ================================

    @RequestMapping(method=RequestMethod.GET, value="/music/composer")
    public Collection<Composer> getComposerList() {

        LOGGER.info("getComposerList()");
        
        return composerDao.getComposers();
    }

    @RequestMapping(method=RequestMethod.GET, value="/music/composer/{name}")
    public Collection<Composer> getComposer(
            @PathVariable String name) {

        LOGGER.info("getComposer name={}", name);

        return composerDao.getComposersByName(name);
    }

    @RequestMapping(method=RequestMethod.POST, value="/music/composer")
    public String storeComposer(
            @RequestBody Composer input) {
        
        LOGGER.info("storeComposer input={}", input);
        
        composerDao.storeComposer(input);
        return "{ \"status\": \"OK\" }";
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/music/composer/{id}")
    public String removeComposer(@PathVariable String id) {

        LOGGER.info("removeComposer id={}", id);
        
        try {
            composerDao.deleteComposer(Long.parseLong(id));
        }
        catch (Exception e) {
            throw new FeriException(e);
        }
        return "{ \"status\": \"OK\" }";
    }

    // ======== Publisher ================================

    @RequestMapping(method=RequestMethod.GET, value="/music/publisher")
    public Collection<Publisher> getPublisherList() {
        
        LOGGER.info("getPublisherList()");
        
        return publisherDao.getPublishers();
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/music/publisher")
    public String storePublisher(
            @RequestBody Publisher input) {
        
        LOGGER.info("storePublisher input={}", input);

        publisherDao.storePublisher(input);
        return "{ \"status\": \"OK\" }";
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/music/publisher/{id}")
    public String removePublisher(@PathVariable long id) {
        
        LOGGER.info("removePublisher id={}", id);
        
        publisherDao.deletePublisher(id);
        return "{ \"status\": \"OK\" }";
    }
}

@ResponseStatus(HttpStatus. NOT_ACCEPTABLE)
class FeriException extends RuntimeException {
    
    public FeriException(Exception e) {
        super("FeriException: " + e);
    }
}
