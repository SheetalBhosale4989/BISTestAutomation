package StepDefinition;

import core.ConfigReaderUtility;
import core.DriverSetupUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class TestHooks {
    @Before
    public void setup() {
        String url = ConfigReaderUtility.geturl();
        DriverSetupUtility.initDriver().get(url);
    }

    @After
    public void tearDown() {
        DriverSetupUtility.quitDriver();
    }
}
