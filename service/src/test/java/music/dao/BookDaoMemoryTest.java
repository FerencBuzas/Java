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

        DataCreator dc = new DataCreator(2);
        ComposerDaoMemory cdm = new ComposerDaoMemory(dc);
        PublisherDaoMemory pdm = new PublisherDaoMemory(dc);
        bdm = new BookDaoMemory(cdm, pdm, dc);
    }

    @Test
    public void testGetBooksByName() throws Exception {

        List<Book> buLi = bdm.getBooks();
        assertTrue(0 < buLi.size());
    }
}
