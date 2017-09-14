package music.dao;

import music.common.Publisher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class PublisherDaoImpl implements PublisherDao {

    private List<Publisher> publishers;

    private void createPublishers() {
        if (publishers == null) {
            publishers = new ArrayList<>();

            publishers = new ArrayList<>();

            publishers.add(new Publisher(1, "Breitkopf"));
            publishers.add(new Publisher(2, "Editio Musica Budapest"));
            publishers.add(new Publisher(3, "Peters"));
        }
    }

    @Override
    public Collection<Publisher> getPublishers() {

        createPublishers();
        return publishers;
    }

    @Override
    public Publisher getPublisherById(long id) {
        createPublishers();
        for (int i = 0; i < publishers.size(); ++i) {
            if (publishers.get(i).id == id) {
                return publishers.get(i);
            }
        }

        // TODO: error handling
        return null;
    }

    @Override
    public Publisher getPublisherByName(String name) {
        createPublishers();
        for (int i = 0; i < publishers.size(); ++i) {
            if (publishers.get(i).name.startsWith(name)) {
                return publishers.get(i);
            }
        }

        // TODO: error handling
        return null;
    }
}
