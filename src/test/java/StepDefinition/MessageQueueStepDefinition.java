package StepDefinition;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.QueueDetailPage;
import Pages.QueuesAndStreamsPage;
import api.MessageSpecification;
import api.PublishMessagePojo;
import core.DriverSetupUtility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MessageQueueStepDefinition {
    WebDriver driver = DriverSetupUtility.getDriver();
    HomePage homepage = new HomePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    QueuesAndStreamsPage queuesAndStreamsPage = new QueuesAndStreamsPage(driver);
    QueueDetailPage queueDetailPage = new QueueDetailPage(driver);
    public static PublishMessagePojo message = new PublishMessagePojo();

    @Given("User is logged in with valid credentials")
    public void login() {
        loginPage.login();
    }

    @And("message is published to queue")
    public void publishMessageToQueue() {
        try {
            message = MessageSpecification.publishMessage(message);
        } catch (Exception e) {
            System.out.println("Error occurred while publishing - " + e.getMessage());
        }
    }

    @When("user navigates to queue and streams tab")
    public void navigateToQueuesTab() {
        if (homepage.isTabsVisible()) {
            homepage.goToQueuesAndStreamsTab();
        }
    }

    @And("user clicks on Queue")
    public void clickOnQueue() {
        Assert.assertTrue(queuesAndStreamsPage.clickQueueElement(message.getRouting_key()));
    }

    //TODO - Acknowledge message and check if Queue is empty
    @And("user fetches a message by clicking on Get message button")
    public void fetchMessage() {
        Assert.assertTrue(queueDetailPage.clickGetMessage());
    }

    @Then("message should be visible")
    public void readMessage() {
        String messagePayload = queueDetailPage.getMessage();
        System.out.println("message is " + messagePayload);
        Assert.assertEquals(messagePayload, message.getPayload());
    }

    @And("user deletes Queue")
    public void deleteQueue() {
        Assert.assertTrue(queueDetailPage.deleteQueue());
    }
}
