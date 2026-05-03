package Pages;

import Utilities.WaitUtility;
import core.ConfigReaderUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    private By username = By.id("username");
    private By password = By.id("password");
    private By loginButton = By.xpath("//input[@type='submit' and @value='Login']");
    private By errorMessage = By.xpath("//div[@class = 'form-popup-warn']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String userid) {
        WaitUtility.waitForVisible(driver,username);
        driver.findElement(username).sendKeys(userid);
    }

    public void enterPassword(String password) {
        WaitUtility.waitForVisible(driver,this.password);
        driver.findElement(this.password).sendKeys(password);
    }

    public void clickLoginButton() {
        WaitUtility.waitForClickable(driver,loginButton);
        driver.findElement(loginButton).click();
    }

    public void login() {
        driver.get(ConfigReaderUtility.get("url"));
        enterUsername(ConfigReaderUtility.get("username"));
        enterPassword(ConfigReaderUtility.get("password"));
        clickLoginButton();
    }

    public String getErrorMessage() {
        WaitUtility.waitForVisible(driver,errorMessage);
        return driver.findElement(errorMessage).getText();
    }
}
