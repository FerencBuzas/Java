package music.common;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PublisherTest {
    private Publisher pubi;

    @Before
    public void setUp() throws Exception {
        pubi = new Publisher("Pubi");
    }

    @Test
    public void testGetters() throws Exception {
        assertEquals("Pubi", pubi.getName());
    }

    @Test
    public void testToString() throws Exception {
        String s = pubi.toString();
        assertTrue(s.contains("Pubi"));
    }
}
