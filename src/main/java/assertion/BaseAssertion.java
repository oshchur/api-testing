package assertion;

import io.restassured.response.Response;
import model.APIResponse;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;

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

    public static void assertServerError(APIResponse apiResp){
        Assert.assertEquals(apiResp.getMessage(), "something bad happened");
        Assert.assertEquals(apiResp.getType(), "unknown");
    }

    public static void assertStatus(Response response, int status){
        Assert.assertEquals(response.getStatusCode(), status);
    }
}
