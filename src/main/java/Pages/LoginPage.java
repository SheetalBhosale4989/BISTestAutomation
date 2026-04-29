package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    private By Username = By.id("username");
    private By Password = By.id("password");
    private By Loginbtn = By.xpath("//input[@type='submit' and @value='Login']");
    private By ErrorMsg = By.xpath("//div[@class = 'form-popup-warn']");


    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }
    public void enterUsername(String userid)
    {
        driver.findElement(Username).sendKeys(userid);
    }
    public void enterPassword(String password)
    {
        driver.findElement(Password).sendKeys(password);
    }
    public void clickLoginbtn()
    {
        driver.findElement(Loginbtn).click();
    }
    public String getErrorMessage()
    {
        return driver.findElement(ErrorMsg).getText();
    }
}
