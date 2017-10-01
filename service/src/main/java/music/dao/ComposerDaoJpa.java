package music.dao;

import music.common.Composer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ComposerDao.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DaoUtil daoUtil;
    
    @Override
    public List<Composer> getComposers() {

        String query = "from Composer";
        return fetchComposers(query);
    }

    @Override
    public List<Composer> getComposersByName(String name) {

        String query = "SELECT p FROM Composer p WHERE p.name LIKE '"+name+"%'";
        return fetchComposers(query);
    }

    @Override
    public void storeComposer(Composer composer) {
        LOGGER.debug("storeComposer({})", composer);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans(entityManager, () -> {
            long id = composer.getId();
            if (id != 0) {
                // Read the original object
                Composer oriComposer = entityManager.find(Composer.class, id);
                if (oriComposer == null) {
                    LOGGER.info("  Could not find composer id=" + id);
                    return ("Could not find composer id=" + id);
                }
                // Modify the original with the new one, rewrite it
                oriComposer.modifyDataByOther(composer);
                entityManager.persist(oriComposer);
            } else {
                entityManager.persist(composer);
            }
            return "";
        });
    }

    @Override
    public void deleteComposer(long id) {
        LOGGER.info("deleteComposer() id={} ##", id);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans(entityManager, () -> {
            Composer composer = entityManager.find(Composer.class, id);
            if (composer != null) {
                entityManager.remove(composer);
            } else {
                LOGGER.info("  Could not find composer id=" + id);
            }
            return "";
        });
    }


    private List<Composer> fetchComposers(String query) {
        LOGGER.debug("fetchComposers() q={} ##", query);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return daoUtil.funcInTrans(entityManager, 
                () -> entityManager.createQuery(query, Composer.class).getResultList());
    }
}
