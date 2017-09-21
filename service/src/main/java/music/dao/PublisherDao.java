package music.dao;

import music.common.Publisher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Give one or more Publisher objects.
 */
@Repository
public interface PublisherDao {

    List<Publisher> getPublishers();

    List<Publisher> getPublishersByName(String name);
}
