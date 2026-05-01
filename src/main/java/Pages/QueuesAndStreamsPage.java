package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class QueuesAndStreamsPage {
    WebDriver driver;
    WebDriverWait wait;

    private By queuesAndStreamsTab = By.id("queues-and-streams");
    private By queuesTable = By.xpath("//div[@id='queues-table-section']");
    private By queueList = By.xpath("//div[@id='queues-table-section']//table[@class='list']/tbody/tr");
    private String queueElementXPathSelector = "//div[@id='queues-table-section']//table[@class='list']/tbody/tr/td/a[normalize-space()='QUEUE_NAME']";

    public QueuesAndStreamsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getQueues() {
        List<WebElement> queues = driver.findElements(queueList);
        return queues;
    }

    public Boolean clickQueueElement(String queueName) {
        String selector = queueElementXPathSelector.replace("QUEUE_NAME", queueName);
        WebElement queueElement = driver.findElement(By.xpath(selector));
        if (queueElement == null) {
            return false;
        }
        queueElement.click();
        return true;
    }
}
