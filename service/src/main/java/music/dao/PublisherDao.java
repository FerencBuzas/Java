package music.dao;

import music.common.Publisher;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PublisherDao {

    Collection<Publisher> getPublishers();

    Publisher getPublisherById(long id);

    Publisher getPublisherByName(String name);
}
