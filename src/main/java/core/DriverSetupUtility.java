package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverSetupUtility {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {

        String browser = ConfigReaderUtility.getBrowser().toLowerCase();


        switch (browser) {

            case "chrome":
                WebDriverManager.chromedriver().setup();

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.default_content_setting_values.notifications", 2);
                prefs.put("profile.password_manager_leak_detection", false);
                prefs.put("safebrowsing.enabled", false);  // IMPORTANT
                prefs.put("safebrowsing.disable_download_protection", true); // IMPORTANT

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);

// Disable password leak detection + password manager UI
                options.addArguments("--disable-features=PasswordLeakDetection");
                options.addArguments("--disable-features=PasswordManagerOnboarding");
                options.addArguments("--disable-features=PasswordManagerEnabled");
                options.addArguments("--disable-features=AutofillServerCommunication");
                options.addArguments("--disable-features=AutofillEnablePasswordManager");
                options.addArguments("--disable-features=AutofillEnableAccountWalletStorage");
                options.addArguments("--disable-save-password-bubble");

// Disable Safe Browsing to stop the popup
                options.addArguments("--safebrowsing-disable-auto-update");
                options.addArguments("--disable-features=SafetyCheck");
                options.addArguments("--disable-features=SecurityInterstitials");
                options.addArguments("--disable-features=SecurityKeyAttestationPrompt");

                driver.set(new ChromeDriver(options));
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

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
