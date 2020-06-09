package api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BaseTest {

    private static final String baseUri = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void basicTest() {
        Response response = given()
                .baseUri(baseUri)
                .queryParam("status", "available")
                .get("findByStatus");
        //.then().log().all().extract().response();
        Assert.assertEquals(response.getStatusCode(), 200, "Error - incorrect status code");
    }
}
