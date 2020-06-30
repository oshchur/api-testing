package assertion;

import io.restassured.response.Response;
import model.APIResponse;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;

public class BaseAssertion {
    private Response response;

    private BaseAssertion() {
    }

    public BaseAssertion(Response response) {
        this.response = response;
    }

    public static void checkResponse(final Response response, final int statusCode) {
        final SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), statusCode, "Error - incorrect status code");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertAll();
    }

    public void checkResponse(LogResponse log, int statusCode){
        switch(log.toString()) {
            case "ALL":
                response.then().log().all();
                break;
            case "STATUS":
                response.then().log().status();
                break;
            case "HEADERS":
                response.then().log().headers();
                break;
            case "BODY":
                response.then().log().body();
            case "IF_ERROR":
                response.then().log().ifError();
            default:
                response.then().log().all();
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), statusCode, "Error - incorrect status code");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertAll();
    }

    //version of previous method with default LogResponse : 'ALL'
    public void checkResponse(int statusCode) {
        checkResponse(LogResponse.ALL, statusCode);
    }

    public static void assertServerError(APIResponse apiResp){
        Assert.assertEquals(apiResp.getMessage(), "something bad happened");
        Assert.assertEquals(apiResp.getType(), "unknown");
    }

    public static void assertStatus(Response response, int status){
        Assert.assertEquals(response.getStatusCode(), status);
    }
}
