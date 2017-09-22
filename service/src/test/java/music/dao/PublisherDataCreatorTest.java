package music.dao;

import music.common.Publisher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class PublisherDataCreatorTest {

    private PublisherDataCreator creator;
    private List<Publisher> publisherList;

    @Before
    public void setUp() throws Exception {

        creator = new PublisherDataCreator();
        publisherList = creator.createPublisherList();
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

        Publisher p = PublisherDataCreator.findPublisherByName(publisherList, "Peters");
        assertNotNull(p);
        assertEquals("Peters", p.getName());
    }

}