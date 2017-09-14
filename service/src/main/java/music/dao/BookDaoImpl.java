package music.dao;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private ComposerDao composerDao;

    @Autowired
    private PublisherDao publisherDao;

    private List<Book> books;

    @Override
    public Collection<Book> getBooks() {

        if (books == null) {
            books = new ArrayList<>();

            Composer bach = composerDao.getComposerByName("Bach");
            Composer beethoven = composerDao.getComposerByName("Beethoven");

            Publisher peters = publisherDao.getPublisherByName("Peters");
            Publisher emb = publisherDao.getPublisherByName("Editio");

            books.add(new Book(1, "Wohltemperiertes Klavier I", bach, peters, 1998));
            books.add(new Book(2, "Wohltemperiertes Klavier II", bach, peters, 1998));
            books.add(new Book(3, "Zongoraszonáták I", beethoven, emb, 1992));
            books.add(new Book(4, "Zongoraszonáták II", beethoven, emb, 1992));
            books.add(new Book(5, "Zongoraszonáták III", beethoven, emb, 1992));
        }

        return books;
    }

    @Override
    public Book getBookById(long id) {
        return null;
    }
}
