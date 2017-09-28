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

        list.add(new Composer("Bach", 1685));
        list.add(new Composer("Haydn", 1732));
        list.add(new Composer("Mozart", 1756));
        list.add(new Composer("Beethoven", 1770));
        list.add(new Composer("Schubert", 1797));
        list.add(new Composer("Schumann", 1810));
        list.add(new Composer("Chopin", 1810));
        list.add(new Composer("Liszt", 1811));
        list.add(new Composer("Brahms", 1833));
        list.add(new Composer("Muszorgszkij", 1839));
//        list.add(new Composer("Tschaikowskij", 1840));
//        list.add(new Composer("Debussy", 1862));
        list.add(new Composer("Rachmaninov", 1873));
        list.add(new Composer("Bartók", 1881));

        return list;
    }

    static Composer findComposerByName(List<Composer> composers, String name) {
        for (Composer composer: composers) {
            if (composer.getName().startsWith(name)) {
                return composer;
            }
        }
        return null;
    }

}
