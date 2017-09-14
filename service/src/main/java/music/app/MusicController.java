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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@ComponentScan(basePackages = "music.dao")
public class MusicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicController.class);

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ComposerDao composerDao;

    @Autowired
    private PublisherDao publisherDao;

    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping("/music/books")
//    public List<Book>(@RequestParam(value="title", defaultValue="World") String name) {
//        LOGGER.info("## music() title={}", name);
//
//        return new Composer(counter.incrementAndGet(), "Beethoven", 1770); // TODO
//    }

    @RequestMapping("/music")
    public String music() {
        LOGGER.info("music");

        return cmd("books") + cmd("composers") + cmd("publishers");
    }

    private static String cmd(String lastWord) {
        return String.format("<a href=\"http://localhost:8080/music/%s\">%s</a><br>",
                lastWord, lastWord);
    }

    @RequestMapping("/music/books")
    public Collection<Book> books() {
        LOGGER.info("music/books");

        return bookDao.getBooks();
    }

    @RequestMapping("/music/composers")
    public Collection<Composer> composers() {
        LOGGER.info("music/composers");

        return composerDao.getComposers();
    }

    @RequestMapping("/music/publishers")
    public Collection<Publisher> publishers() {
        LOGGER.info("music/publishers");

        return publisherDao.getPublishers();
    }
}
