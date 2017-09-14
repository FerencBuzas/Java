package music.dao;

import music.common.Publisher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherDao {

    List<Publisher> getPublishers();

    List<Publisher> getPublishersByName(String name);

    Publisher getPublisherById(long id);
}
