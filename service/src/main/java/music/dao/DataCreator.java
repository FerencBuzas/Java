package music.dao;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * This class is used only with JPA: creates and persists all the data.
 */
@Service
@ComponentScan(basePackages = "music.common, music.dao")
public class DataCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataCreator.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ComposerDataCreator composerDataCreator;

    @Autowired
    private PublisherDataCreator publisherDataCreator;

    @Autowired
    private BookDataCreator bookDataCreator;

    public void createData() {
        LOGGER.info("DataCreator.createData()");

        // Create lists in memory
        List<Composer> composers = composerDataCreator.createComposerList();
        List<Publisher> publishers = publisherDataCreator.createPublisherList();

        // Store the lists with JPA.
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for (Composer composer: composers) {
            entityManager.persist(composer);
        }
        for (Publisher publisher: publishers) {
            entityManager.persist(publisher);
        }

        // Create and store dependent Books after Composers and Publishers are stored.
        List<Book> books = bookDataCreator.createBookList(composers, publishers);
        for (Book book: books) {
            entityManager.persist(book);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
