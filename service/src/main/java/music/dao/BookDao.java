package music.dao;

import music.common.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@ComponentScan
@Repository
public interface BookDao {

    List<Book> getBooks();

    Book getBookById(long id);
}
