package api;

import core.ConfigReaderUtility;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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

    //TODO - create exchange. use json file to load request body.
    public static PublishMessagePojo publishMessage(PublishMessagePojo message) throws Exception {
        try {
            message.setVhost(ConfigReaderUtility.get("virtual_host"));
            message.setName("amq.default");
            message.setRouting_key(createQueue());
            message.setPayload("Hello from API!");
            message.setPayload_encoding("string");
            message.setProperties(new HashMap<>());

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
            System.out.println("Status code " + response.statusCode());
            System.out.println("Response " + response.asString());
            return message;
        } catch (Exception e) {
            System.out.println("Error occurred while publishing - " + e.getMessage());
            throw e;
        }
    }

    public static String createQueue() throws Exception {
        CreateQueuePojo createQueuePojo = new CreateQueuePojo();
        createQueuePojo.setDurable(true);
        createQueuePojo.setAuto_delete(false);

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
