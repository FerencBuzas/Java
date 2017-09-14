package music.app;

import music.common.Book;
import music.common.Composer;
import music.dao.BookDaoImpl;
import music.dao.ComposerDaoImpl;
import music.dao.PublisherDaoImpl;
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
    private BookDaoImpl bookDao;

    @Autowired
    private ComposerDaoImpl composerDao;

    @Autowired
    private PublisherDaoImpl publisherDao;

    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping("/music/books")
//    public List<Book>(@RequestParam(value="title", defaultValue="World") String name) {
//        LOGGER.info("## music() title={}", name);
//
//        return new Composer(counter.incrementAndGet(), "Beethoven", 1770); // TODO
//    }

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
}
