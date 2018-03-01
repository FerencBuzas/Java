package music.dao;

import music.common.Composer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Implementation of ComposerDao, when data are persisted.
 */
@Repository
@Profile({"dev", "default"})
public class ComposerDaoJpa implements ComposerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComposerDao.class);

    private final EntityManagerFactory entityManagerFactory;
    private final DaoUtil daoUtil;
    
    @Autowired
    public ComposerDaoJpa(EntityManagerFactory entityManagerFactory,
                          DaoUtil daoUtil) {

        this.entityManagerFactory = entityManagerFactory;
        this.daoUtil = daoUtil;
    }
    
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

        EntityManager em = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans(em, () -> {
            
            long id = composer.getId();
            if (id != 0) {
                // Read the original object
                Composer oriComposer = em.find(Composer.class, id);
                if (oriComposer == null) {
                    LOGGER.info("  Could not find composer id=" + id);
                    return ("Could not find composer id=" + id);
                }
                
                // Modify the original with the new one, rewrite it
                oriComposer.modifyDataByOther(composer);
                em.persist(oriComposer);
            }
            else {  // new object
                em.persist(composer);
            }
            return "";
        });
    }

    @Override
    public void deleteComposer(long id) {
        LOGGER.info("deleteComposer() id={}", id);

        EntityManager em = entityManagerFactory.createEntityManager();
        daoUtil.funcInTrans(em, () -> {
            
            Composer composer = em.find(Composer.class, id);
            if (composer != null) {
                em.remove(composer);
            }
            else {
                LOGGER.info("  Could not find composer id=" + id);
            }
            return "";
        });
    }


    private List<Composer> fetchComposers(String query) {
        LOGGER.debug("fetchComposers() q={}", query);

        EntityManager em = entityManagerFactory.createEntityManager();
        return daoUtil.funcInTrans(em, 
                () -> em.createQuery(query, Composer.class).getResultList());
    }
}
