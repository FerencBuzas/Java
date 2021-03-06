package music.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Ferenc_Buzas on 13 Sep 2017
 */
@Entity
public class Composer implements MusicObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(Composer.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int birthYear;


    public Composer() {
        LOGGER.debug("Composer()");

    }

    // Note: the Id must not be set when managed by JPA
    public Composer(long id, String name, int birthYear) {
        LOGGER.debug("Composer() name={} birthYear={}", name, birthYear);

        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }
    
    public Composer(String name, int birthYear) {
        this(0, name, birthYear);
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void modifyDataByOther(Composer other) {
        name = other.getName();
        birthYear = other.birthYear;
    }
    
    public String toString() {

        return "Composer[id=" +id+ ", name=" +name+ " birthYear=" + birthYear +"]";
    }
}
