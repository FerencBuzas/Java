package music.dao;

import music.common.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Implementation of BookDao, when data are persisted.
 */
@Repository
@Profile({"dev", "default"})
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
        
        EntityManager em = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans( em, () -> {
            
            long id = book.getId();
            if (id != 0) {
            
                // Read the original object
                Book oriBook = em.find(Book.class, id);
                if (oriBook == null) {
                    LOGGER.info("  Could not find book id=" + id);
                    return ("Could not find book id=" + id);
                }
                // Modify the original with the new one, rewrite it
                oriBook.modifyDataByOther(book);
                em.persist(oriBook);
            }
            else {  // a new object
                em.persist(book);
            }
            return "";
        });
    }
    
    @Override
    public void deleteBook(long id) {
        LOGGER.info("deleteBook() id={}", id);
        
        EntityManager em = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans(em, () -> {
            Book book = em.find(Book.class, id);
            if (book != null) {
                em.remove(book);
            } else {
                LOGGER.info("  Could not find book id=" + id);
            }
            return "";
        });
    }

    private List<Book> fetchBooks(String query) {
            
        EntityManager em = entityManagerFactory.createEntityManager();
        return daoUtil.funcInTrans(em, () ->
            em.createQuery(query, Book.class).getResultList());
    }
}
