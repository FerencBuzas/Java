package music.dao;

import music.common.Book;
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

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<Book> getBooks() {

        String query = "SELECT p FROM book";
        return fetchBooks(query);
    }

    @Override
    public Book getBookById(long id) {

        return null;
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
