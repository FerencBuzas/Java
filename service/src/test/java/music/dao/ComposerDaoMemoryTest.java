package music.dao;

import music.common.Composer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ComposerDaoMemoryTest {
    
    private ComposerDataCreator pdc;
    private ComposerDaoMemory pdm;

    @Before
    public void setUp() {

        pdc = new ComposerDataCreator();
        pdm = new ComposerDaoMemory(pdc);
    }

    @Test
    public void testGetComposersByName() throws Exception {

        List<Composer> puLi = pdm.getComposersByName("Mozart");

        assertEquals(1, puLi.size());
        assertTrue(puLi.get(0).getName().contains("Mozart"));
    }
}