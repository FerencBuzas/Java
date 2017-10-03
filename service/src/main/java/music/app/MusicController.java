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
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin     // Adds Allow Cross Origin to header; TODO: restrict to address ...
@ComponentScan(basePackages = "music.common, music.dao")
public class MusicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicController.class);

    private static final String URL_BASE = "localhost:8080/music";

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ComposerDao composerDao;

    @Autowired
    private PublisherDao publisherDao;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/music")
    public String music() {
        return link("book") + link("composer") + link("publisher");
    }

    private static String link(String lastWord) {
        return String.format("<a href=\"%s/%s\">%s</a><br>", URL_BASE, lastWord, lastWord);
    }

    // ======== Book ================================
    
    @RequestMapping(method=RequestMethod.GET, value="/music/book")
    public Collection<Book> books() {
        return bookDao.getBooks();
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/music/book")
    public String removeBook(
            @RequestParam(value="id") String id) {
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
    public Collection<Composer> composers(
            @RequestParam(value="name", defaultValue="") String name) {

        if (name.isEmpty()) {
            return composerDao.getComposers();
        }
        else {
            return composerDao.getComposersByName(name);
        }
    }

    @RequestMapping(method=RequestMethod.POST, value="/music/composer")
    public String storeComposer(
            @RequestBody Composer input) {
        
        LOGGER.info("storeComposer input={}", input);
        
        composerDao.storeComposer(input);
        return "{ \"status\": \"OK\" }";
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/music/composer")
    public String removeComposer(
            @RequestParam(value="id") String id) {

        LOGGER.info("removeComposer id={}", id);
        
        composerDao.deleteComposer(Long.parseLong(id));
        return "{ \"status\": \"OK\" }";
    }

    // ======== Publisher ================================

    @RequestMapping(method=RequestMethod.GET, value="/music/publisher")
    public Collection<Publisher> publishers(
            @RequestParam(value="name", defaultValue="") String name) {
        
        Collection<Publisher> result = publisherDao.getPublishers();
        LOGGER.info("publishers() returning {} elements", result.size());
        return result;
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/music/publisher")
    public String storePublisher(
            @RequestBody Publisher input) {
        
        LOGGER.info("storePublisher input={}", input);

        publisherDao.storePublisher(input);
        return "{ \"status\": \"OK\" }";
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/music/publisher")
    public String removePublisher(
            @RequestParam(value="id") long id) {
        
        LOGGER.info("removePublisher id={}", id);
        
        publisherDao.deletePublisher(id);
        return "{ \"status\": \"OK\" }";
    }
}
