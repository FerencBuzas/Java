package music.dao;

import music.common.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Give one or more Book objects.
 */
@ComponentScan
@Repository
public interface BookDao {

    List<Book> getBooks();

    ResponseEntity<?> addBook(Book book);

    void deleteBook(long id);
}
