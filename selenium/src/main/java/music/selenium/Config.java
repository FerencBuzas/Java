package music.selenium;

public class Config {

    private String baseUrl = "localhost:3000";
    private int sleepShortMs = 500;
    private int sleepMiddleMs = 2000;
    private int sleepLongMs = 4000;
    private int timeoutMs = 1500;
    private String chromeDriverPropName = "webdriver.chrome.driver";
    private String firefoxDriverPropName = "webdriver.gecko.driver";

    public String getBaseUrl() {
        return baseUrl;
    }

    public int getSleepShortMs() {
        return sleepShortMs;
    }

    public int getSleepMiddleMs() {
        return sleepMiddleMs;
    }

    public int getSleepLongMs() {
        return sleepLongMs;
    }

    public int getTimeoutMs() {
        return timeoutMs;
    }

    public String getChromeDriverPropName() {
        return chromeDriverPropName;
    }

    public String getFirefoxDriverPropName() {
        return firefoxDriverPropName;
    }
}
