package music.dao;

import music.common.Composer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ComposerDao, when data are served just from memory.
 */
@Repository
public class ComposerDaoMemory implements ComposerDao {

    private List<Composer> composers;

    @Autowired
    ComposerDaoMemory(ComposerDataCreator composerDataCreator) {
        composers = composerDataCreator.createComposerList();
    }

    @Override
    public List<Composer> getComposers() {

        return composers;
    }

    @Override
    public List<Composer> getComposersByName(String name) {

        List<Composer> result = new ArrayList<>();
        for (int i = 0; i < composers.size(); ++i) {
            if (composers.get(i).name.startsWith(name)) {
                result.add(composers.get(i));
            }
        }

        return result;
    }

    @Override
    public Composer getComposerById(long id) {

        for (int i = 0; i < composers.size(); ++i) {
            if (composers.get(i).id == id) {
                return composers.get(i);
            }
        }

        return null;
    }
}
