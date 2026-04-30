package StepDefinition;

import Pages.HomePage;
import Pages.LoginPage;
import core.ConfigReaderUtility;
import core.DriverSetupUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginStepDefinition {
    WebDriver driver = DriverSetupUtility.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homepage = new HomePage(driver);

    @Given("user is on login page")
    public void userOnLoginPage() {
        String url = ConfigReaderUtility.getUrl();
        driver.get(url);
    }

    @When("user logs in with username {string} and password {string}")
    public void login(String user, String password) {
        loginPage.enterUsername(user);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Then("user should see {string}")
    public void verifyLoginStatus(String loginStatus) {
        boolean isLoginSuccessful = homepage.isLogoutBtnVisible();
        if (isLoginSuccessful) {
            Assert.assertTrue(homepage.isLogoutBtnVisible(), loginStatus);
        }
    }

    @Then("user should see error message {string}")
    public void invalidLogin(String errorMessage) {
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains(errorMessage));
    }
}
