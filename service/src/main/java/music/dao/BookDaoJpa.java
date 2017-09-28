package music.dao;

import music.common.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.net.URI;
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

    @Override
    public List<Book> getBooks() {
        LOGGER.debug("getBooks()");
        
        String query = "from Book";
        return fetchBooks(query);
    }

    @Override
    public ResponseEntity<?> addBook(Book book) {
        LOGGER.debug("addBook({})", book);
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(book);
        
        entityManager.getTransaction().commit();
        entityManager.close();
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(location).build();    }

    @Override
    public void deleteBook(long id) {
        LOGGER.info("deleteBook() id={} ##", id);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        // Find managed Entity reference
        Book book = entityManager.find(Book.class, id);
        if (book != null){
            entityManager.remove(book);
        }
        else {
            LOGGER.info("  Could not find book id=" + id);
        }
        
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private List<Book> fetchBooks(String query) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Book> result = entityManager.createQuery(query, Book.class ).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }
}
