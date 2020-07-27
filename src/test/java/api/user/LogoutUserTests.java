package api.user;

import assertion.BaseAssertion;
import client.UserClient;
import io.restassured.response.Response;
import listeners.Listener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

@Listeners(Listener.class)
public class LogoutUserTests {
    UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void logoutTest() {
        Response response = userClient.logout();
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }
}
