package music.dao;

import music.common.Composer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ComposerDataCreatorTest {
    
    private ComposerDataCreator creator;
    private List<Composer> composerList;

    @Before
    public void setUp() throws Exception {

        creator = new ComposerDataCreator();
        composerList = creator.createComposerList();
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

}
