package music.dao;

import music.common.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BookDaoMemoryTest {
    
    private BookDaoMemory bdm;

    @Before
    public void setUp() {

        DataCreator dc = new DataCreator();
        dc.createData(2, false);
        bdm = new BookDaoMemory(dc);
    }

    @Test
    public void testGetBooksByName() {

        List<Book> buLi = bdm.getBooks();
        assertTrue(0 < buLi.size());
    }
}
