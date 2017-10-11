package music.dao;

import music.common.Composer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ComposerDaoMemoryTest {
    
    private DataCreator dc;
    private ComposerDaoMemory cdm;

    @Before
    public void setUp() {

        dc = new DataCreator();
        cdm = new ComposerDaoMemory(dc);
    }

    @Test
    public void testGetComposersByName() throws Exception {

        List<Composer> puLi = cdm.getComposersByName("Mozart");

        assertEquals(1, puLi.size());
        assertTrue(puLi.get(0).getName().contains("Mozart"));
    }
}
