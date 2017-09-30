package music.dao;

import music.common.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Implementation of PublisherDao, when data are persisted.
 */
@Repository
@Primary
public class PublisherDaoJpa implements PublisherDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherDaoJpa.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DaoUtil daoUtil;

    @Override
    public List<Publisher> getPublishers() {
        LOGGER.info("getPublishers() ##");

        String query = "from Publisher";
        return fetchPublishers(query);
    }

    @Override
    public List<Publisher> getPublishersByName(String name) {

        String query = "SELECT p FROM Publisher p WHERE name LIKE '" + name + "%'";
        return fetchPublishers(query);
    }

    @Override
    public void storePublisher(Publisher publisher) {
        LOGGER.info("storePublisher() {} ##", publisher);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans(entityManager, () -> {
            entityManager.persist(publisher);
            return "";
        });
    }

//    public void deleteMusicObject(Class clazz, long id, EntityManagerFactory emf) {
//    }

    @Override
    public void deletePublisher(long id) {
        LOGGER.info("deletePublisher() id={} ##", id);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans(entityManager, () -> {
            Publisher publisher = entityManager.find(Publisher.class, id);
            if (publisher != null) {
                entityManager.remove(publisher);
            } else {
                LOGGER.info("  Could not find publisher id=" + id);
            }
            return "";
        });
    }

    private List<Publisher> fetchPublishers(String query) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return daoUtil.funcInTrans(entityManager, () -> entityManager.createQuery(query, Publisher.class).getResultList());
    }
}
