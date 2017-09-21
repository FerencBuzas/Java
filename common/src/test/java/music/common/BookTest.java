package music.common;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookTest {

    private Composer compi;
    private Publisher pubi;
    private Book book;

    @Before
    public void setUp() {
        compi = new Composer("Compi", 2001);
        pubi = new Publisher("Pubi");
        book = new Book("title", compi, pubi, 2011);
    }

    @Test
    public void testGetters() throws Exception {
        assertNotNull(book);
        assertEquals("title", book.getTitle());
        assertEquals(compi, book.getComposer());
        assertEquals(pubi, book.getPublisher());
        assertEquals(2011, book.getPubYear());
    }

    @Test
    public void testToString() throws Exception {
        String s = book.toString();
        assertTrue(s.contains("Compi"));
        assertTrue(s.contains("Pubi"));
    }
}