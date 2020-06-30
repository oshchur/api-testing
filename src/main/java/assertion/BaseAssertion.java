package assertion;

import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import model.APIResponse;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;

public class BaseAssertion {

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

    public static void assertServerError(APIResponse apiResp){
        Assert.assertEquals(apiResp.getMessage(), "something bad happened");
        Assert.assertEquals(apiResp.getType(), "unknown");
    }

    public static void assertStatus(Response response, int status){
        Assert.assertEquals(response.getStatusCode(), status);
    }

}
