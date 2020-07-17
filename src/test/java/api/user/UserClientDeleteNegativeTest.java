package api.user;

import assertion.BaseAssertion;
import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;

public class UserClientDeleteNegativeTest extends BaseUserClientTest {
    @Test
    public void deleteUnexistingUser() {
        User user = userBuilder.constructRandomValidUser();
        Response response = userClient.delete(user.getUsername());
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_NOT_FOUND);
    }

    @Test
    public void tryToDeleteOneUserTwice() {
        User user = userBuilder.constructRandomValidUser();
        SoftAssert softAssert = new SoftAssert();
        Response response = userClient.create(user);
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);
        response = userClient.delete(user.getUsername());
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);
        response = userClient.delete(user.getUsername());
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_NOT_FOUND);
        softAssert.assertAll();
    }

    @Test
    public void deleteEmptyNameUser() {
        Response response = userClient
                .delete("");
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_BAD_METHOD);
    }
}
