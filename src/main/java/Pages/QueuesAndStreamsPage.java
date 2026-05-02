package Pages;

import core.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class QueuesAndStreamsPage {
    WebDriver driver;

    private String queueElementXPathSelector = "//div[@id='queues-table-section']//table[@class='list']/tbody/tr/td/a[normalize-space()='QUEUE_NAME']";

    public QueuesAndStreamsPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean clickQueueElement(String queueName) {
        String selector = queueElementXPathSelector.replace("QUEUE_NAME", queueName);
        WaitUtility.waitForVisible(driver, By.xpath(selector));
        WebElement queueElement = driver.findElement(By.xpath(selector));
        if (queueElement == null) {
            return false;
        }
        queueElement.click();
        return true;
    }


}
