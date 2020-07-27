package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {
    private final String baseUrl = "https://petstore.swagger.io/v2";

    protected final RequestSpecification baseRequestSpecification(final ContentType contentType, LogDetail logDetail) {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setConfig(RestAssuredConfig.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .setConfig(RestAssuredConfig.config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true)))
                .setBaseUri(baseUrl)
                .setAccept(contentType)
                .setContentType(contentType);

        if (logDetail != null) {
            builder.log(logDetail);
        }
        return builder.build();
    }

    protected final RequestSpecification baseRequestSpecification(final ContentType contentType) {
        return baseRequestSpecification(contentType, null);
    }

    protected final RequestSpecification baseRequestSpecification(LogDetail logDetail) {
        return baseRequestSpecification(ContentType.JSON, logDetail);
    }

    protected final RequestSpecification baseRequestSpecification() {
        return baseRequestSpecification(ContentType.JSON, null);
    }
}
