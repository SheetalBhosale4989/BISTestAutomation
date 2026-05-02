package Pages;

import core.WaitUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class QueueDetailPage {
    WebDriver driver;

    public QueueDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    private By getMessageHeader = By.xpath("//div[@class='section-hidden section-invisible']//h2[text()='Get messages']");
    private By getMessageButton = By.xpath("//input[@type='submit' and @value='Get Message(s)']");
    private By messagePayload = By.xpath("//table[@class='facts']//pre[@class=\"msg-payload\"]");
    private By deleteHeader = By.xpath("//div[@id='delete']");
    private By deleteQueueButton = By.xpath("//input[@type='submit' and @value='Delete Queue']");

    public Boolean clickGetMessage() {
        WaitUtility.waitForClickable(driver, getMessageHeader).click();
        WaitUtility.waitForClickable(driver, getMessageButton).click();
        return true;
    }

    public String getMessage() {
        String messageText = WaitUtility.waitForVisible(driver, messagePayload).getText();
        return messageText;
    }

    public Boolean deleteQueue() {
        WaitUtility.waitForClickable(driver, deleteHeader).click();
        WaitUtility.waitForClickable(driver, deleteQueueButton).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return true;
    }
}
