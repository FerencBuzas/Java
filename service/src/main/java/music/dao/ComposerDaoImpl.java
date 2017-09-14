package music.dao;

import music.common.Composer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ComposerDaoImpl implements ComposerDao {

    private List<Composer> composers = new ArrayList<>();

    private void createComposers() {
        if (composers.size() == 0) {
            composers.add(new Composer(1, "Bach", 1685));
            composers.add(new Composer(2, "Haydn", 1732));
            composers.add(new Composer(3, "Mozart", 1756));
            composers.add(new Composer(4, "Beethoven", 1770));
            composers.add(new Composer(5, "Schubert", 1797));
        }
    }

    @Override
    public Collection<Composer> getComposers() {

        createComposers();
        return composers;
    }

    @Override
    public Composer getComposerById(long id) {
        createComposers();

        for (int i = 0; i < composers.size(); ++i) {
            if (composers.get(i).id == id) {
                return composers.get(i);
            }
        }

        // TODO: add error handling
        return null;
    }

    @Override
    public Composer getComposerByName(String name) {
        createComposers();

        for (int i = 0; i < composers.size(); ++i) {
            if (composers.get(i).name.startsWith(name)) {
                return composers.get(i);
            }
        }

        // TODO: add error handling
        return null;
    }
}
