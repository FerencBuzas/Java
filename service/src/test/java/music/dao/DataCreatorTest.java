package music.dao;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataCreatorTest {
    
    private DataCreator creator;

    @Before
    public void setUp() throws Exception {
        
        creator = new DataCreator();
    }

    @Test
    public void createBookList() throws Exception {

        // Check if "Mozart" is in the list.
        for (Book p : creator.getBooks()) {
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
        for (Composer p : creator.getComposers()) {
            if ("Mozart".equals(p.getName())) {
                return;
            }
        }
        fail("No Mozart in the list");
    }

    @Test
    public void findComposerByName() throws Exception {

        Composer p = creator.findComposerByName("Mozart");
        assertNotNull(p);
        assertEquals("Mozart", p.getName());
    }
    
    @Test
    public void createPublisherList() throws Exception {

        // Check if "Peters" is in the list.
        for (Publisher p : creator.getPublishers()) {
            if ("Peters".equals(p.getName())) {
                return;
            }
        }
        fail("No Peters in the list");
    }

    @Test
    public void findPublisherByName() throws Exception {

        Publisher p = creator.findPublisherByName("Peters");
        assertNotNull(p);
        assertEquals("Peters", p.getName());
    }

}
