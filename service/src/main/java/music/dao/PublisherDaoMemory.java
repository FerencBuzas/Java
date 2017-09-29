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
        for (Publisher publisher : publishers) {
            if (publisher.getName().startsWith(name)) {
                result.add(publisher);
            }
        }
        return result;
    }

    @Override
    public void addPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    @Override
    public void deletePublisher(long id) {
        publishers.remove(id);
    }
}
