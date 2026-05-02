package api;

import Utilities.JsonUtilities;
import core.ConfigReaderUtility;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class MessageSpecification {
    private static final RequestSpecification spec;

    static {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReaderUtility.get("url"))
                .setContentType("application/json")
                .setAuth(RestAssured.basic(
                        ConfigReaderUtility.get("username"),
                        ConfigReaderUtility.get("password")))
                .build();
    }

    public static PublishMessagePojo publishMessage(PublishMessagePojo message) throws Exception {
        try {
            String payloadMessage = "Automated Test Message " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            message = JsonUtilities.readJsonAsObject("src/test/resources/payloads/PublishMessage.json", PublishMessagePojo.class);
            message.setRouting_key(createQueue());
            message.setPayload(payloadMessage);
            String virtualHost = URLEncoder.encode(message.getVhost(), StandardCharsets.UTF_8);
            Response response = RestAssured
                    .given()
                    .spec(spec)
                    .body(message)
                    .when()
                    .urlEncodingEnabled(false)
                    .post("api/exchanges/" + virtualHost + "/amq.default/publish")
                    .then()
                    .extract()
                    .response();

            if (response.statusCode() != 200) {
                throw new Exception("Error while receiving response for publishing message.");
            }

            boolean routed = response.jsonPath().getBoolean("routed");
            if (!routed) {
                throw new Exception("Error while publishing message - not routed.");
            }

            return message;
        } catch (Exception e) {
            System.out.println("Error occurred while publishing - " + e.getMessage());
            throw e;
        }
    }

    public static String createQueue() throws Exception {

        CreateQueuePojo createQueuePojo =
                JsonUtilities.readJsonAsObject(
                        "src/test/resources/payloads/CreateQueue.json",
                        CreateQueuePojo.class
                );
        String virtualHost = URLEncoder.encode(ConfigReaderUtility.get("virtual_host"), StandardCharsets.UTF_8);

        String queueName = URLEncoder.encode("AutomationTest" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), StandardCharsets.UTF_8);

        System.out.println("Queue name: " + queueName);

        Response response = RestAssured
                .given()
                .spec(spec)
                .body(createQueuePojo)
                .when()
                .urlEncodingEnabled(false)
                .put("api/queues/" + virtualHost + "/" + queueName)
                .then()
                .extract()
                .response();

        if (response.statusCode() != 201) {
            throw new Exception("Error while creating queue");
        }

        return queueName;
    }

}
