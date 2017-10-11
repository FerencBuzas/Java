package music.dao;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DataCreatorTest {
    
    private DataCreator creator;
    private List<Book> bookList;
    private List<Composer> composerList;
    private List<Publisher> publisherList;

    @Before
    public void setUp() throws Exception {
        
        creator = new DataCreator();
        composerList = creator.createComposerList();
        publisherList = creator.createPublisherList();
        bookList = creator.createBookList(composerList, publisherList);
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
    
    @Test
    public void createComposerList() throws Exception {

        // Check if "Mozart" is in the list.
        for (Composer p : composerList) {
            if ("Mozart".equals(p.getName())) {
                return;
            }
        }
        fail("No Mozart in the list");
    }

    @Test
    public void findComposerByName() throws Exception {

        Composer p = creator.findComposerByName(composerList, "Mozart");
        assertNotNull(p);
        assertEquals("Mozart", p.getName());
    }
    
    @Test
    public void createPublisherList() throws Exception {

        // Check if "Peters" is in the list.
        for (Publisher p : publisherList) {
            if ("Peters".equals(p.getName())) {
                return;
            }
        }
        fail("No Peters in the list");
    }

    @Test
    public void findPublisherByName() throws Exception {

        Publisher p = creator.findPublisherByName(publisherList, "Peters");
        assertNotNull(p);
        assertEquals("Peters", p.getName());
    }

}
