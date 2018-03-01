package music.dao;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DataCreatorTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
            
    private DataCreator creator;

    @Before
    public void setUp() {

        creator = new DataCreator(entityManagerFactory);
        creator.createData(2, false);
    }

    @Test
    public void testCreateBookList() {

        // Check if "Bach" is in the list.
        for (Book p : creator.getBooks()) {
            if ("Piano sonatas".equals(p.getTitle())) {
                assertNotNull(p.getComposer());
                assertEquals("Haydn", p.getComposer().getName());
                assertNotNull(p.getPublisher());
                assertTrue(p.getPublisher().getName().startsWith("Peters"));
                return;
            }
        }
        fail("No 'Piano sonatas' in the list");
    }
    
    @Test
    public void testCreateComposerList() {

        // Check if "Bach" is in the list.
        for (Composer p : creator.getComposers()) {
            if ("Bach".equals(p.getName())) {
                return;
            }
        }
        fail("No Bach in the list");
    }

    @Test
    public void testFindComposerByName() {

        Composer p = creator.findComposerByName("Bach");
        assertNotNull(p);
        assertEquals("Bach", p.getName());
    }
    
    @Test
    public void testCreatePublisherList() {

        // Check if "Peters" is in the list.
        for (Publisher p : creator.getPublishers()) {
            if ("Peters".equals(p.getName())) {
                return;
            }
        }
        fail("No Peters in the list");
    }

    @Test
    public void testFindPublisherByName() {

        Publisher p = creator.findPublisherByName("Peters");
        assertNotNull(p);
        assertEquals("Peters", p.getName());
    }
}
