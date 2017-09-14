package music.dao;

import music.common.Composer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ComposerDaoImpl implements ComposerDao {

    private List<Composer> composers;

    private void createComposers() {
        if (composers != null) {
            return;
        }
        composers = new ArrayList<>();
        composers.add(new Composer(1, "Bach", 1685));
        composers.add(new Composer(2, "Haydn", 1732));
        composers.add(new Composer(3, "Mozart", 1756));
        composers.add(new Composer(4, "Beethoven", 1770));
        composers.add(new Composer(5, "Schubert", 1797));
    }

    @Override
    public List<Composer> getComposers() {

        createComposers();
        return composers;
    }

    @Override
    public List<Composer> getComposersByName(String name) {
        createComposers();

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
        createComposers();

        for (int i = 0; i < composers.size(); ++i) {
            if (composers.get(i).id == id) {
                return composers.get(i);
            }
        }

        return null;
    }
}
