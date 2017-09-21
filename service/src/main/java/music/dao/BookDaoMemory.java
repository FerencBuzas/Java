package music.dao;

import music.common.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of BookDao, when data are served just from memory.
 */
@Repository
public class BookDaoMemory implements BookDao {

    @Autowired
    private ComposerDaoMemory composerDao;

    @Autowired
    private PublisherDaoMemory publisherDao;

    @Autowired
    private BookDataCreator bookDataCreator;

    private List<Book> books;

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
}
