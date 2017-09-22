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

        ComposerDataCreator cdc = new ComposerDataCreator();
        ComposerDaoMemory cdm = new ComposerDaoMemory(cdc);
        PublisherDataCreator pdc = new PublisherDataCreator();
        PublisherDaoMemory pdm = new PublisherDaoMemory(pdc);
        BookDataCreator bookDataCreator = new BookDataCreator();
        bdm = new BookDaoMemory(cdm, pdm, bookDataCreator);
    }

    @Test
    public void testGetBooksByName() throws Exception {

        List<Book> buLi = bdm.getBooks();
        assertTrue(0 < buLi.size());
    }
}
