package StepDefinition;

import core.ConfigReaderUtility;
import core.DriverSetupUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class TestHooks {
    @Before
    public void setup() {
        String url = ConfigReaderUtility.getUrl();
        DriverSetupUtility.initDriver().get(url);
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverSetupUtility.getDriver())
                    .getScreenshotAs(
                            OutputType.BYTES);

            scenario.attach(screenshot, "image/png", "Failed Screenshot");
        }
    }

    @After(order = 0)
    public void quitBrowser() {
        DriverSetupUtility.quitDriver();
    }
}
