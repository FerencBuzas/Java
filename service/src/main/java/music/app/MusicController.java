package music.app;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import music.dao.BookDao;
import music.dao.ComposerDao;
import music.dao.DataCreator;
import music.dao.PublisherDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin     // Adds Allow Cross Origin to header; TODO: restrict to address ...
@ComponentScan(basePackages = "music.dao")
public class MusicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicController.class);

    private static final String URL_BASE = "localhost:8080/music";

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ComposerDao composerDao;

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private DataCreator dataCreator;

    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping("/music/books")
//    public List<Book>(@RequestParam(value="title", defaultValue="World") String name) {
//
//        return new Composer(counter.incrementAndGet(), "Beethoven", 1770); // TODO
//    }

    @RequestMapping("/music")
    public String music() {
        return cmd("book") + cmd("composer") + cmd("publisher");
    }

    private static String cmd(String lastWord) {
        return String.format("<a href=\"%s/%s\">%s</a><br>", URL_BASE, lastWord, lastWord);
    }

    @RequestMapping("/music/create")
    public String create() {
        return dataCreator.createData(composerDao, publisherDao);
    }

    @RequestMapping("/music/book")
    public Collection<Book> books() {
        return bookDao.getBooks();
    }

    @RequestMapping("/music/composer")
    public Collection<Composer> composers(@RequestParam(value="name", defaultValue="") String name) {
        if (name.isEmpty()) {
            return composerDao.getComposers();
        }
        else {
            return composerDao.getComposersByName(name);
        }
    }

    @RequestMapping("/music/publisher")
    public Collection<Publisher> publishers(@RequestParam(value="name", defaultValue="") String name) {
        return publisherDao.getPublishers();
    }
}
