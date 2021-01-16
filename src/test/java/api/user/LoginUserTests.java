package api.user;

import assertion.BaseAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import listeners.Listener;
import model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class LoginUserTests {
    UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void loginTest() {
        User user = new UserBuilder().constructRandomValidUser();
        Response response = userClient.login(user.getUsername(), user.getPassword());
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }
}
