package music.dao;

import music.common.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a list of Publisher objects; it can be used in memory, or persisted.
 */
@Service
class PublisherDataCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherDataCreator.class);

    List<Publisher> createPublisherList() {

        List<Publisher> list = new ArrayList<>();

        list.add(new Publisher("Breitkopf"));
        list.add(new Publisher("Editio Musica Budapest"));
        list.add(new Publisher("Peters"));

        return list;
    }

    Publisher findPublisherByName(List<Publisher> publishers, String name) {
        for (Publisher publisher: publishers) {
            if (publisher.getName().startsWith(name)) {
                return publisher;
            }
        }
        return null;
    }
}
