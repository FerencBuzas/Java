package music.dao;

import music.common.Composer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComposerDao {

    List<Composer> getComposers();

    List<Composer> getComposersByName(String name);

    Composer getComposerById(long id);
}
