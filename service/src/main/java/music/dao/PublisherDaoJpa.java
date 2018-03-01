package music.dao;

import music.common.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Implementation of PublisherDao, when data are persisted.
 */
@Repository
@Profile({"dev", "default"})
public class PublisherDaoJpa implements PublisherDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherDaoJpa.class);

    private final EntityManagerFactory entityManagerFactory;
    private final DaoUtil daoUtil;

    @Autowired
    public PublisherDaoJpa(EntityManagerFactory entityManagerFactory,
            DaoUtil daoUtil) {

        this.entityManagerFactory = entityManagerFactory;
        this.daoUtil = daoUtil;
    }


    @Override
    public List<Publisher> getPublishers() {
        LOGGER.info("getPublishers()");

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
        LOGGER.info("storePublisher() {}", publisher);

        EntityManager em = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans(em, () -> {
            
            long id = publisher.getId();
            if (id != 0) {
                
                // Read the original object
                Publisher oriPublisher = em.find(Publisher.class, id);
                if (oriPublisher == null) {
                    LOGGER.info("  Could not find publisher id=" + id);
                    return ("Could not find publisher id=" + id);
                }
                
                // Modify the original with the new one, rewrite it
                oriPublisher.modifyDataByOther(publisher);
                em.persist(oriPublisher);
            }
            else {
                em.persist(publisher);
            }
            return "";
        });
    }

    @Override
    public void deletePublisher(long id) {
        LOGGER.info("deletePublisher() id={}", id);

        EntityManager em = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans(em, () -> {
            
            Publisher publisher = em.find(Publisher.class, id);
            if (publisher != null) {
                em.remove(publisher);
            }
            else {
                LOGGER.info("  Could not find publisher id=" + id);
            }
            return "";
        });
    }

    private List<Publisher> fetchPublishers(String query) {
        EntityManager em = entityManagerFactory.createEntityManager();

        return daoUtil.funcInTrans(em, () -> em.createQuery(query, Publisher.class).getResultList());
    }
}
