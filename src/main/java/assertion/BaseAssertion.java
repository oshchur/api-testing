package assertion;

import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class BaseAssertion {

    private BaseAssertion() {

    }

    public static void checkResponse(final Response response, final int statusCode) {
        final SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), statusCode, "Error - incorrect status code");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertAll();
    }
}
