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

    private final DataCreator dataCreator;

    @Autowired
    public BookDaoMemory(DataCreator dataCreator) {
        this.dataCreator = dataCreator;
    }


    @Override
    public List<Book> getBooks() {
        return dataCreator.getBooks();
    }

    @Override
    public void storeBook(Book book) {
        dataCreator.getBooks().add(book);
    }

    @Override
    public void deleteBook(long id) {
        
        dataCreator.getBooks().stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .ifPresent(b -> dataCreator.getBooks().remove(b));
    }
}
