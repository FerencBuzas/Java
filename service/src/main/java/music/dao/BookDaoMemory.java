package music.dao;

import music.common.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of BookDao, when data are served just from memory.
 */
@Repository
@Profile("test")
public class BookDaoMemory implements BookDao {

    private ComposerDaoMemory composerDao;
    private PublisherDaoMemory publisherDao;
    private BookDataCreator bookDataCreator;

    private List<Book> books;

    @Autowired
    BookDaoMemory(ComposerDaoMemory composerDao,
            PublisherDaoMemory publisherDao,
            BookDataCreator bookDataCreator) {
        this.composerDao = composerDao;
        this.publisherDao = publisherDao;
        this.bookDataCreator = bookDataCreator;
    }

    /*
     * This cannot be in the constructor, as bookDataCreator needs other two DAOs,
     *   which are also injected; setting the order of creation would be difficult to me.
     */
    private void createDataIfNotYet() {
        if (books == null) {
            books = bookDataCreator.createBookList(composerDao.getComposers(),
                    publisherDao.getPublishers());
        }
    }

    @Override
    public List<Book> getBooks() {

        createDataIfNotYet();

        return books;
    }

    @Override
    public void storeBook(Book book) {
        books.add(book);
    }

    @Override
    public void deleteBook(long id) {
        books.remove(id);
    }
}
