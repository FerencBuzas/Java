package music.client;

import music.common.Book;
import music.common.Composer;
import music.common.Publisher;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * ClientApp, which uses Spring RestTemplate to get an answer from a fixed web page.
 *
 * Taken from: https://spring.io/guides/gs/consuming-rest/
 *
 * Created by Ferenc_Buzas on 11/4/2016.
 */
public class ClientApp {

    private static final String URL = "http://localhost:8080/music";

    // Note: class Composer is defined in module 'common'
    private static void fetchAndWriteObjects() {

        // Not using console, since that would fail in e.g. CI; write to System.out

        RestTemplate restTemplate = new RestTemplate();
        List<Composer> composers = restTemplate.getForObject(URL+"/composer", List.class);
        List<Publisher> publishers = restTemplate.getForObject(URL+"/publisher", List.class);
        List<Book> books = restTemplate.getForObject(URL+"/book", List.class);

        System.out.println("Composers: ");
//        // TODO: simplified loops do not work.
//        composers.forEach(c -> System.out.println("  "+c));
//        for (Composer c: composers) { ...
        for (int i = 0; i < composers.size(); ++i) {
            System.out.println("  "+composers.get(i));
        }

        System.out.println("Publishers: ");
//        publishers.forEach(p -> System.out.println("  "+p));
        for (int i = 0; i < publishers.size(); ++i) {
            System.out.println("  "+publishers.get(i));
        }

        System.out.println("Books: ");
//        books.forEach(b -> System.out.println("  "+b));
        for (int i = 0; i < books.size(); ++i) {
            System.out.println("  "+books.get(i));
        }
    }

    public static void main(String args[]) {
        fetchAndWriteObjects();
    }
}
