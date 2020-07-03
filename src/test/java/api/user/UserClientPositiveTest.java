package api.user;

import assertion.BaseAssertion;
import assertion.UserAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.List;

public class UserClientPositiveTest {
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

    @Test
    public void createWithList() {
        final List<User> randomUsers = userBuilder.constructRandomListValidUsers(3);
        final Response response = userClient.createWithList(randomUsers);

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }

    @Test
    public void getUserByUsername() {
        final Response response = userClient.getUserByUsername("Malina");
        final User user = response.as(User.class);

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
        UserAssertion.checkValidUser(user, expectedUser);
    }

    @Test
    public void updateUserByUsername() {
        final User user = userClient.getUserByUsername("Malina").as(User.class);
        user.setLastName("Malynovskyi");

        Response response = userClient.updateByUsername("Malina", user);

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }
}
