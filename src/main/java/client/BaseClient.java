package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {
    private final String baseUrl = "https://petstore.swagger.io/v2";

    protected final RequestSpecification baseRequestSpecification(final ContentType contentType) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setAccept(contentType)
                .setContentType(contentType)
                .build();
    }
}
