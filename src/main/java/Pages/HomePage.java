package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    private By LogoutBtn = By.xpath("//input[@type='submit' and @value='Log out']");
    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }
    public boolean isLogoutBtnVisible()
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LogoutBtn));
        return driver.findElement(LogoutBtn).isDisplayed();
    }
}
