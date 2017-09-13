package hello;

/**
 * A bean with an id and a string.
 *
 * Created by Ferenc_Buzas on 11/4/2016.
 */
public class Person {

    private final String id;
    private final String descr;

    public Person() {
        this.id = "(empty)";
        this.descr = null;
    }

    public Person(String id) {
        this.id = id;
        switch (id) {
            case "1":  this.descr = "Macine"; break;
            case "2":  this.descr = "Maci"; break;
            case "3":  this.descr = "Bea"; break;
            case "4":  this.descr = "Marci"; break;
            default:   this.descr = "(default)"; break;
        }
    }

    public String getId() {
        return id;
    }

    public String getDescr() {
        return descr;
    }

    public String toString() {
        return "Person: " + descr;
    }
}
