package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.*;

public abstract class BaseClient {
    private final String baseUrl = "https://petstore.swagger.io/v2";

    protected final RequestSpecification baseRequestSpecification = new RequestSpecBuilder()
            .setBaseUri(baseUrl)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .build();
}
