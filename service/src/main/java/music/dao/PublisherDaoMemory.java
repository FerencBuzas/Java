package music.dao;

import music.common.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of PublisherDao, when data are served just from memory.
 */
@Repository
@Profile("test")
public class PublisherDaoMemory implements PublisherDao {

    private final DataCreator dataCreator;

    @Autowired
    public PublisherDaoMemory(DataCreator dataCreator) {
        this.dataCreator = dataCreator;
    }

    @Override
    public List<Publisher> getPublishers() {

        return dataCreator.getPublishers();
    }

    @Override
    public List<Publisher> getPublishersByName(String name) {

        List<Publisher> result = new ArrayList<>();
        for (Publisher publisher : dataCreator.getPublishers()) {
            if (publisher.getName().startsWith(name)) {
                result.add(publisher);
            }
        }
        return result;
    }

    @Override
    public void storePublisher(Publisher publisher) {
        dataCreator.getPublishers().add(publisher);
    }

    @Override
    public void deletePublisher(long id) {
        dataCreator.getPublishers().remove(id);
    }
}
