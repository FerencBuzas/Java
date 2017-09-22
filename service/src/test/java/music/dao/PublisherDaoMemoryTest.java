package music.dao;

import music.common.Publisher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PublisherDaoMemoryTest {

    private PublisherDataCreator pdc;
    private PublisherDaoMemory pdm;

    @Before
    public void setUp() {

        pdc = new PublisherDataCreator();
        pdm = new PublisherDaoMemory(pdc);
    }

    @Test
    public void testGetPublishersByName() throws Exception {

        List<Publisher> puLi = pdm.getPublishersByName("Peters");

        assertEquals(1, puLi.size());
        assertTrue(puLi.get(0).getName().contains("Peters"));
    }

}