package music.dao;

import music.common.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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

        return dataCreator.getPublishers().stream()
                .filter(p -> p.getName().startsWith(name))
                .collect(Collectors.toList());
    }

    @Override
    public void storePublisher(Publisher publisher) {
        dataCreator.getPublishers().add(publisher);
    }

    @Override
    public void deletePublisher(long id) {
        dataCreator.getPublishers().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .ifPresent(p -> dataCreator.getPublishers().remove(p));
    }
}
