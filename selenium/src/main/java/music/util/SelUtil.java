package music.util;

import music.selenium.Config;

// Utilities for Selenium Testing
public class SelUtil {

    private final Config config;

    public SelUtil(Config config) {
        this.config = config;
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Sleep " + millis + " interrupted.");
        }
    }
    
    public void sleepShort() {
        sleep(config.getSleepShortMs());
    }

    public void sleepMiddle() {
        sleep(config.getSleepMiddleMs());
    }

    public void sleepLong() {
        sleep(config.getSleepLongMs());
    }
}
