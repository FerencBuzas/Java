package music.dao;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDataCreatorTest {
    
    private BookDataCreator creator;
    private List<Book> bookList;

    @Before
    public void setUp() throws Exception {

        List<Composer> liCo = new ComposerDataCreator().createComposerList();
        List<Publisher> liPu = new PublisherDataCreator().createPublisherList();

        creator = new BookDataCreator();
        bookList = creator.createBookList(liCo, liPu);
    }

    @Test
    public void createBookList() throws Exception {

        // Check if "Mozart" is in the list.
        for (Book p : bookList) {
            if ("Sonate h-moll".equals(p.getTitle())) {
                assertNotNull(p.getComposer());
                assertEquals("Liszt", p.getComposer().getName());
                assertNotNull(p.getPublisher());
                assertTrue(p.getPublisher().getName().startsWith("Editio"));
                return;
            }
        }
        fail("No 'Sonate h-moll' in the list");
    }
}