package music.dao;

import music.common.Composer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a list of Composer objects; it can be used in memory, or persisted.
 */
@Service
class ComposerDataCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComposerDataCreator.class);

    List<Composer> createComposerList() {

        List<Composer> list = new ArrayList<>();

        list.add(new Composer(1, "Bach", 1685));
        list.add(new Composer(2, "Haydn", 1732));
        list.add(new Composer(3, "Mozart", 1756));
        list.add(new Composer(4, "Beethoven", 1770));
        list.add(new Composer(5, "Schubert", 1797));

        return list;
    }

    static Composer findComposerByName(List<Composer> composers, String name) {
        for (Composer composer: composers) {
            if (composer.name.startsWith(name)) {
                return composer;
            }
        }
        return null;
    }

}
