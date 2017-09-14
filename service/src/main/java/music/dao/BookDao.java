package music.dao;

import music.common.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@ComponentScan
@Repository
public interface BookDao {

    Collection<Book> getBooks();

    Book getBookById(long id);
}
