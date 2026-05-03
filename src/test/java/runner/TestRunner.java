package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Arrays;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "StepDefinition",
//        tags = "@debug",
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        Object[][] scenarios = super.scenarios();

        Arrays.sort(scenarios, (a, b) -> {
            Integer orderA = extractOrder((PickleWrapper) a[0]);
            Integer orderB = extractOrder((PickleWrapper) b[0]);
            return orderA.compareTo(orderB);
        });

        return scenarios;
    }

    private Integer extractOrder(PickleWrapper pickle) {

        return pickle.getPickle().getTags().stream()
                .filter(t -> t.startsWith("@order="))
                .map(t -> Integer.parseInt(t.split("=")[1]))
                .findFirst()
                .orElse(999);
    }

    @Test(dataProvider = "scenarios", retryAnalyzer = RetryAnalyzer.class)
    public void runScenario(io.cucumber.testng.PickleWrapper pickle,
                            io.cucumber.testng.FeatureWrapper feature) {
        super.runScenario(pickle, feature);
    }
}

