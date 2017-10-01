package music.dao;

import music.common.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Implementation of BookDao, when data are persisted.
 */
@Repository
@Primary
public class BookDaoJpa implements BookDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BookDaoJpa.class);
    
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Autowired
    private DaoUtil daoUtil;

    @Override
    public List<Book> getBooks() {
        LOGGER.debug("getBooks()");
        
        String query = "from Book";
        return fetchBooks(query);
    }

    @Override
    public void storeBook(Book book) {
        LOGGER.debug("storeBook({})", book);
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans( entityManager, () -> {
            long id = book.getId();
            if (id != 0) {
                // Read the original object
                Book oriBook = entityManager.find(Book.class, id);
                if (oriBook == null) {
                    LOGGER.info("  Could not find book id=" + id);
                    return ("Could not find book id=" + id);
                }
                // Modify the original with the new one, rewrite it
                oriBook.modifyDataByOther(book);
                entityManager.persist(oriBook);
            } else {
                entityManager.persist(book);
            }
            return "";
        });
    }
    
    @Override
    public void deleteBook(long id) {
        LOGGER.info("deleteBook() id={} ##", id);
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans(entityManager, () -> {
            Book book = entityManager.find(Book.class, id);
            if (book != null) {
                entityManager.remove(book);
            } else {
                LOGGER.info("  Could not find book id=" + id);
            }
            return "";
        });
    }

    private List<Book> fetchBooks(String query) {
            
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return daoUtil.funcInTrans(entityManager, () ->
            entityManager.createQuery(query, Book.class).getResultList());
    }
}
