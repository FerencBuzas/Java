package music.util;

// Utilities for Selenium Testing
public class SelUtil {

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Sleep " + millis + " interrupted.");
        }
    }
}
