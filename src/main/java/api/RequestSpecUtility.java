package api;

import core.ConfigReaderUtility;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecUtility {
    private static RequestSpecification spec;

    public static RequestSpecification getSpec() {
        if (spec == null) {
            spec = new RequestSpecBuilder()
                    .setBaseUri(ConfigReaderUtility.get("url"))
                    .setContentType("application/json")
                    .setAuth(RestAssured.basic(
                            ConfigReaderUtility.get("username"),
                            ConfigReaderUtility.get("password")))
                    .build();
        }
        return spec;
    }
}
