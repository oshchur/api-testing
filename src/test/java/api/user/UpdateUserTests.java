package api.user;

import assertion.BaseAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class UpdateUserTests {
    UserClient userClient;
    UserBuilder userBuilder;
    User user;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userBuilder = new UserBuilder();
        user = userBuilder.build();
    }

    @Test
    public void updateByEmptyUsername() {
        final Response response = userClient.updateByUsername("", user);

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_BAD_METHOD);
    }

    @Test
    public void updateByInvalidUsername() {
        final Response response = userClient.updateByUsername("Orevo", user);

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }

    @Test
    public void updateUserByUsername() {
        final User user = userClient.getUserByUsername("Malina").as(User.class);
        user.setLastName("Malynovskyi");

        Response response = userClient.updateByUsername("Malina", user);

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }

}
