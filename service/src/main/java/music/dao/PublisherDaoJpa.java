package music.dao;

import music.common.Publisher;
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

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<Publisher> getPublishers() {

        String query = "from Publisher";
        return fetchPublishers(query);
    }

    @Override
    public List<Publisher> getPublishersByName(String name) {

        String query = "SELECT p FROM Publisher p WHERE name LIKE '"+name+"%'";
        return fetchPublishers(query);
    }

    private List<Publisher> fetchPublishers(String query) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Publisher> result = entityManager.createQuery(query, Publisher.class ).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }
}
