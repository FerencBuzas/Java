package music.common;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComposerTest {

    private Composer compi;

    @Before
    public void setUp() throws Exception {
        compi = new Composer("Compi", 2001);
    }

    @Test
    public void testGetters() throws Exception {
        assertEquals("Compi", compi.getName());
        assertEquals(2001, compi.getBirthYear());
    }

    @Test
    public void testToString() throws Exception {
        String s = compi.toString();
        assertTrue(s.contains("Compi"));
        assertTrue(s.contains("2001"));
    }
}
