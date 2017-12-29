package music.dao;

import music.common.Composer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of ComposerDao, when data are served just from memory.
 */
@Repository
@Profile("test")
public class ComposerDaoMemory implements ComposerDao {

    private final DataCreator dataCreator;

    @Autowired
    public ComposerDaoMemory(DataCreator dataCreator) {
        this.dataCreator = dataCreator;
    }

    @Override
    public List<Composer> getComposers() {

        return dataCreator.getComposers();
    }

    @Override
    public List<Composer> getComposersByName(String name) {

        return dataCreator.getComposers().stream()
                .filter(c -> c.getName().startsWith(name))
                .collect(Collectors.toList());
    }

    @Override
    public void storeComposer(Composer composer) {
        dataCreator.getComposers().add(composer);
    }

    @Override
    public void deleteComposer(long id) {
        dataCreator.getComposers().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .ifPresent(c -> dataCreator.getComposers().remove(c));
    }
}
