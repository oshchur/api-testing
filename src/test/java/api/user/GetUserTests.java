package api.user;

import assertion.BaseAssertion;
import assertion.UserAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import listeners.Listener;
import model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class GetUserTests {
    UserClient userClient;
    UserBuilder userBuilder;
    User expectedUser;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userBuilder = new UserBuilder();
        expectedUser = userBuilder
                .setUserName("Malina")
                .setFirstName("Taras")
                .setLastName("Malynovskyi")
                .setEmail("tarasmalynovskyy@gmail.com")
                .setPassword("!Qwerty123")
                .setPhone("+3806333333")
                .setUserStatus(0)
                .build();
    }

    @Test(dataProvider = "getInvalidUsernames")
    public void getUserByInvalidUsername(final String username) {
        final Response response = userClient.getUserByUsername(username);
        final User user = response.as(User.class);

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_NOT_FOUND);
        UserAssertion.checkInvalidUser(user);
    }

    @DataProvider
    private Object[] getInvalidUsernames() {
        return new Object[]{
                "Tarantino",
                "5656",
                " ",
                "флдж",
                "*/!@##!@%$!",
                "4322___vfdjkqWW",
                "     ",
                "  asd221342 4 324 ",};
    }

    @Test
    public void getUserByUsername() {
        final Response createUserResponse = userClient.create(expectedUser);
        final Response getUserByUsernameResponse = userClient.getUserByUsername("Malina");
        final User user = getUserByUsernameResponse.as(User.class);

        BaseAssertion.checkResponse(createUserResponse, HttpURLConnection.HTTP_OK);
        BaseAssertion.checkResponse(getUserByUsernameResponse, HttpURLConnection.HTTP_OK);
        UserAssertion.checkValidUser(user, expectedUser);
    }
}
