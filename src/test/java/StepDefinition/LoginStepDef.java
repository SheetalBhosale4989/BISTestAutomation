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
import org.testng.annotations.Test;


public class LoginStepDef {
    WebDriver driver = DriverSetupUtility.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homepage = new HomePage(driver);
    @Given("user is on login page")
    public void user_on_login_page() {
        // already handled in BaseTest

    }

    @When("user logs in with username {string} and password {string}")
    public void login(String user, String pass) {
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        loginPage.clickLoginbtn();
    }

    @Then("user should see {string}")
    public void verifyLoginStatus(String loginStatus) {
     boolean isLoginSuccessful = homepage.isLogoutBtnVisible();
     if(isLoginSuccessful==true)
     Assert.assertTrue(homepage.isLogoutBtnVisible(),loginStatus);
    }

    @Then("user should see error message {string}")
    public void invalidLogin(String errorMessage) {
    String errorMsg = loginPage.getErrorMessage();
    Assert.assertTrue(errorMsg.contains(errorMessage));

    }
}
