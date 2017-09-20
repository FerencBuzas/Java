package music.dao;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a list of Book objects; it can be used in memory, or persisted.
 */
@Service
public class BookDataCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookDataCreator.class);

    List<Book> createBookList(ComposerDao composerDao, PublisherDao publisherDao) {

        List<Book> list = new ArrayList<>();

        Composer bach = composerDao.getComposersByName("Bach").stream().findFirst().get();
        Composer beethoven = composerDao.getComposersByName("Beethoven").stream().findFirst().get();
        Composer liszt = composerDao.getComposersByName("Liszt").stream().findFirst().get();

        Publisher peters = publisherDao.getPublishersByName("Peters").stream().findFirst().get();
        Publisher emb = publisherDao.getPublishersByName("Editio").stream().findFirst().get();

        list.add(new Book(1, "Wohltemperiertes Klavier I", bach, peters, 1998));
        list.add(new Book(2, "Wohltemperiertes Klavier II", bach, peters, 1998));
        list.add(new Book(3, "Zongoraszonáták I", beethoven, emb, 1992));
        list.add(new Book(4, "Zongoraszonáták II", beethoven, emb, 1992));
        list.add(new Book(5, "Zongoraszonáták III", beethoven, emb, 1992));
        list.add(new Book(6, "Sonate h-moll", liszt, emb, 1988));
        return list;
    }
}
