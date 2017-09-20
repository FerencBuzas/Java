package music.dao;

import music.common.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of PublisherDao, when data are served just from memory.
 */
@Repository
public class PublisherDaoMemory implements PublisherDao {

    private List<Publisher> publishers;

    @Autowired
    PublisherDaoMemory(PublisherDataCreator publisherDataCreator) {
        publishers = publisherDataCreator.createPublisherList();
    }

    @Override
    public List<Publisher> getPublishers() {

        return publishers;
    }

    @Override
    public List<Publisher> getPublishersByName(String name) {

        List<Publisher> result = new ArrayList<>();
        for (int i = 0; i < publishers.size(); ++i) {
            if (publishers.get(i).name.startsWith(name)) {
                result.add(publishers.get(i));
            }
        }
        return result;
    }

    @Override
    public Publisher getPublisherById(long id) {

        for (int i = 0; i < publishers.size(); ++i) {
            if (publishers.get(i).id == id) {
                return publishers.get(i);
            }
        }

        // TODO: error handling
        return null;
    }
}
