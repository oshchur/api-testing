package assertion;

import io.restassured.response.Response;
import model.User;
import org.testng.asserts.SoftAssert;

public class UserAssertion {

    private UserAssertion() {

    }

    public static void checkValidUser(final Response response, final User user) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Error - incorrect status code");
        softAssert.assertEquals(response.contentType(), "application/json", "Error - incorrect content type");
        softAssert.assertEquals(user.getUsername(), "Malina", "Error - incorrect username");
        softAssert.assertAll();
    }

    public static void checkInvalidUser(final Response response, final User user) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 404, "Error - incorrect status code");
        softAssert.assertEquals(response.contentType(), "application/json", "Error - incorrect content type");
        softAssert.assertEquals(user.getUsername(), null, "Error - incorrect username");
        softAssert.assertAll();
    }
}
