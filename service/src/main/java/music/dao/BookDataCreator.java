package music.dao;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a list of Book objects; it can be used in memory, or persisted.
 */
@Service
public class BookDataCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookDataCreator.class);
    
    private final ComposerDataCreator composerDataCreator;
    private final PublisherDataCreator publisherDataCreator;

    @Autowired
    public BookDataCreator(ComposerDataCreator composerDataCreator,
            PublisherDataCreator publisherDataCreator) {

        this.composerDataCreator = composerDataCreator;
        this.publisherDataCreator = publisherDataCreator;
    }
       
    List<Book> createBookList(List<Composer> composerList, List<Publisher> publisherList) {

        List<Book> list = new ArrayList<>();

        Composer bach = composerDataCreator.findComposerByName(composerList, "Bach");
        Composer beethoven = composerDataCreator.findComposerByName(composerList, "Beethoven");
        Composer liszt = composerDataCreator.findComposerByName(composerList, "Liszt");

        Publisher peters = publisherDataCreator.findPublisherByName(publisherList, "Peters");
        Publisher emb = publisherDataCreator.findPublisherByName(publisherList, "Editio");

        list.add(new Book("Wohltemperiertes Klavier I", bach, peters, 1998));
        list.add(new Book("Wohltemperiertes Klavier II", bach, peters, 1998));
        list.add(new Book("Zongoraszonáták I", beethoven, emb, 1992));
        list.add(new Book("Zongoraszonáták II", beethoven, emb, 1992));
        list.add(new Book("Zongoraszonáták III", beethoven, emb, 1992));
        list.add(new Book("Sonate h-moll", liszt, emb, 1988));
        return list;
    }
}
