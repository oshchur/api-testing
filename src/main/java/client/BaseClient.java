package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {
    private final String baseUrl = "https://petstore.swagger.io/v2";

    protected final RequestSpecification baseRequestSpecification(final ContentType contentType, LogDetail logDetail) {
        
        return new RequestSpecBuilder()
                .addFilter(new ErrorLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .setBaseUri(baseUrl)
                .setAccept(contentType)
                .setContentType(contentType)
                .build();
    }

    //additional version of method with default LogDetail : 'ALL', ContentType : 'JSON'
    protected final RequestSpecification baseRequestSpecification(final ContentType contentType) {
        return baseRequestSpecification(contentType, LogDetail.ALL);
    }

    protected final RequestSpecification baseRequestSpecification(LogDetail logDetail) {
        return baseRequestSpecification(ContentType.JSON, logDetail);
    }

    protected final RequestSpecification baseRequestSpecification() {
        return baseRequestSpecification(ContentType.JSON, LogDetail.ALL);
    }
}
