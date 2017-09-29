package music.dao;

import music.common.Composer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Give one or more Composer objects.
 */
@Repository
public interface ComposerDao {

    List<Composer> getComposers();

    List<Composer> getComposersByName(String name);

    void addComposer(Composer composer);

    void deleteComposer(long id);
}
