package assertion;

import io.restassured.internal.http.Status;
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

    public static void checkResponse(final Response response, final Status statusCode) {
        final SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(statusCode.matches(response.statusCode()), "Error - status code is out of bound expectation");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertAll();
    }
}
