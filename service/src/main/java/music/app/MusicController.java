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
import java.util.List;
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

    // TODO: use RequestMethod everywhere

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

    @RequestMapping(value="/music/book/store")
    public String storeBook(
            @RequestParam(value="id", defaultValue="0") long id,
            @RequestParam(value="title") String title,
            @RequestParam(value="composer") String composerName,
            @RequestParam(value="publisher") String publisherName,
            @RequestParam(value="pubYear") int pubYear) {
        
        LOGGER.info("storeBook title={}", title);
        
        List<Composer> composers = composerDao.getComposersByName(composerName);
        int nCo = composers.size();  
        if (nCo != 1) {
            return "{ \"status\": \"Failure (number of composers: "+nCo+")\"";
        }
        
        List<Publisher> publishers = publisherDao.getPublishersByName(publisherName);
        int nPu = publishers.size();
        if (nPu != 1) {
            return "{ \"status\": \"Failure (number of publishers: "+nPu+")\"";
        }
        
        bookDao.storeBook(new Book(id, title, composers.get(0), publishers.get(0), pubYear));
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

    @RequestMapping(value="/music/composer/store")
    public String storeComposer(
            @RequestParam(value="id", defaultValue="0") long id,
            @RequestParam(value="name") String name,
            @RequestParam(value="birthYear") int birthYear) {
        
        LOGGER.info("storeComposer id={} name={}", id, name);

        composerDao.storeComposer(new Composer(id, name, birthYear));
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
    
    @RequestMapping(value="/music/publisher/store")
    public String storePublisher(
            @RequestParam(value="id", defaultValue="0") long id,
            @RequestParam(value="name") String name) {
        
        LOGGER.info("storePublisher name={}", name);

        publisherDao.storePublisher(new Publisher(name));
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
