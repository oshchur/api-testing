package assertion;

import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class BaseAssertion {

    public static void checkResponse(Response response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Error - incorrect status code");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertAll();
    }
}
