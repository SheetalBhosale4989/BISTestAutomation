package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetupUtility {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver()
    {

        String browser = ConfigReaderUtility.getBrowser().toLowerCase();


        switch (browser) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }

        driver.get().manage().window().maximize();
        return driver.get();
    }

    public static WebDriver getDriver()
    {
        return driver.get();
    }

    public static void quitDriver()
    {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
