package music.dao;

import music.common.Composer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Implementation of ComposerDao, when data are persisted.
 */
@Repository
@Primary
public class ComposerDaoJpa implements ComposerDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<Composer> getComposers() {

        String query = "SELECT p FROM composer";
        return fetchComposers(query);
    }

    @Override
    public List<Composer> getComposersByName(String name) {

        String query = "SELECT p FROM composer WHERE name LIKE '"+name+"%'";
        return fetchComposers(query);
    }

    @Override
    public Composer getComposerById(long id) {

        return null;
    }

    private List<Composer> fetchComposers(String query) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Composer> result = entityManager.createQuery(query, Composer.class ).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }
}
