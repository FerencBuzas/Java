package music.consumer;

import music.common.Composer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * ConsumerApp, which uses Spring RestTemplate to get an answer from a fixed web page.
 *
 * Taken from: https://spring.io/guides/gs/consuming-rest/
 *
 * Created by Ferenc_Buzas on 11/4/2016.
 */
public class ConsumerApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerApp.class);
    private static final String URL = "http://localhost:8080/music?title=TestFeri";

    // Note: class Composer is defined in module 'common'
    private static void consumeGreeting() {

        RestTemplate restTemplate = new RestTemplate();
        Composer composer = restTemplate.getForObject(URL, Composer.class);

        LOGGER.info("App, GREETING: {}", composer.toString());
    }

    public static void main(String args[]) {
        consumeGreeting();
    }
}
