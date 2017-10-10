package music.dao;

import music.common.Composer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ComposerDao, when data are served just from memory.
 */
@Repository
@Profile("test")
public class ComposerDaoMemory implements ComposerDao {

    private List<Composer> composers;

    @Autowired
    public ComposerDaoMemory(ComposerDataCreator composerDataCreator) {
        composers = composerDataCreator.createComposerList();
    }

    @Override
    public List<Composer> getComposers() {

        return composers;
    }

    @Override
    public List<Composer> getComposersByName(String name) {

        List<Composer> result = new ArrayList<>();
        for (Composer composer : composers) {
            if (composer.getName().startsWith(name)) {
                result.add(composer);
            }
        }

        return result;
    }

    @Override
    public void storeComposer(Composer composer) {
        composers.add(composer);
    }

    @Override
    public void deleteComposer(long id) {
        composers.remove(id);
    }
}
