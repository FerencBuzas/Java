package music.dao;

import music.common.Book;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BookDaoMemoryTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    private BookDaoMemory bdm;

    @Before
    public void setUp() {

        DataCreator dc = new DataCreator(entityManagerFactory);
        dc.createData(2, false);
        bdm = new BookDaoMemory(dc);
    }

    @Test
    public void testGetBooksByName() {

        List<Book> buLi = bdm.getBooks();
        assertTrue(0 < buLi.size());
    }
}
