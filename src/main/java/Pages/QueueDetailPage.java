package Pages;

import core.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QueueDetailPage {
    WebDriver driver;
    WebDriverWait wait;

    public QueueDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    private By getMessageHeader = By.xpath("//h2[text()='Get messages']");
    private By getMessageButton = By.xpath("//input[@type='submit' and @value='Get Message(s)']");
    private By messagePayload = By.xpath("//table[@class='facts']//pre[@class=\"msg-payload\"]");

    public void clickGetMessage() {
        WaitUtility.waitForClickable(driver, getMessageHeader).click();
        WaitUtility.waitForClickable(driver, getMessageButton).click();
    }

    public String getMessage() {

        String messageText = WaitUtility.waitForVisible(driver, messagePayload).getText();
        return messageText;
    }
}
