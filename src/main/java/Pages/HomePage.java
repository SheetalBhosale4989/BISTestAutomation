package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    private By tabs = By.id("tabs");
    private By overviewTab = By.id("overview");
    private By connectionsTab = By.id("connections");
    private By channelsTab = By.id("channels");
    private By exchangesTab = By.id("exchanges");
    private By queuesAndStreamsTab = By.id("queues-and-streams");

    private By logoutButton = By.xpath("//input[@type='submit' and @value='Log out']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToOverviewTab() {
        driver.findElement(overviewTab).click();
    }

    public void goToConnectionsTab() {
        driver.findElement(connectionsTab).click();
    }

    public void goToChannelsTab() {
        driver.findElement(channelsTab).click();
    }

    public void goToExchangesTab() {
        driver.findElement(exchangesTab).click();
    }

    public void goToQueuesAndStreamsTab() {
        driver.findElement(queuesAndStreamsTab).click();
    }

    public boolean isLogoutBtnVisible() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        return driver.findElement(logoutButton).isDisplayed();
    }

    public boolean isTabsVisible() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabs));
        return driver.findElement(tabs).isDisplayed();
    }
}
