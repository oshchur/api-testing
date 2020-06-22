package assertion;

import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class BaseAssertion {

    private BaseAssertion() {

    }

    public static void checkResponse(final Response response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Error - incorrect status code");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertAll();
    }

    public static void checkNegativeResponse(final Response response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 404, "Error - incorrect status code");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertAll();
    }
}
