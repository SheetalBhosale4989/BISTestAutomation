package Pages;

import core.WaitUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class QueueDetailPage {
    WebDriver driver;

    public QueueDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    private By queueNameSelector = By.xpath("//div[@id='main']/h1/b");
    private By getMessageHeader = By.xpath("//div[@class='section-hidden section-invisible']//h2[text()='Get messages']");
    private By getMessageButton = By.xpath("//input[@type='submit' and @value='Get Message(s)']");
    private By messagePayload = By.xpath("//table[@class='facts']//pre[@class=\"msg-payload\"]");
    private By deleteHeader = By.xpath("//div[@id='delete']");
    private By deleteQueueButton = By.xpath("//input[@type='submit' and @value='Delete Queue']");
    private By selectAckMode = By.name("ackmode");
    private By numberOfMessagesText = By.xpath("//div[@id='msg-wrapper']/div[@class='box']/p");

    public String getQueueName(){
        WaitUtility.waitForVisible(driver, queueNameSelector);
        return driver.findElement(queueNameSelector).getText();
    }

    public Boolean userClicksOnGetMessagesHeader() {
        WaitUtility.waitForClickable(driver, getMessageHeader).click();
        return true;
    }

    public Boolean selectAutomaticAckMode() {
        WaitUtility.waitForVisible(driver, selectAckMode);
        WebElement dropdown = driver.findElement(selectAckMode);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Automatic ack");
        return true;
    }

    public Boolean clickGetMessage() {
        WaitUtility.waitForClickable(driver, getMessageButton).click();
        return true;
    }

    public String getMessage() {
        String messageText = WaitUtility.waitForVisible(driver, messagePayload).getText();
        return messageText;
    }

    public String verifyNumberOfMessagesInQueue() {
        WaitUtility.waitForVisible(driver, numberOfMessagesText);
        return driver.findElement(numberOfMessagesText).getText();
    }

    public Boolean deleteQueue() {
        WaitUtility.waitForClickable(driver, deleteHeader).click();
        WaitUtility.waitForClickable(driver, deleteQueueButton).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return true;
    }
}
