package music.dao;

import music.common.Composer;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ComposerDao {

    Collection<Composer> getComposers();

    Composer getComposerById(long id);

    Composer getComposerByName(String name);
}
