package api.user;

import assertion.BaseAssertion;
import assertion.UserAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class UserClientNegativeTest {
    UserClient userClient;
    UserBuilder userBuilder;
    User user;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userBuilder = new UserBuilder();
    }

    @Test
    public void createByEmptyList() {
        Response response = userClient.createWithList(new ArrayList<>());

        BaseAssertion.checkResponse(response, Status.SUCCESS);
    }

    @Test(dataProvider = "getInvalidUsernames")
    public void getUserByInvalidUsername(final String username) {
        final Response response = userClient.getUserByUsername(username);
        final User user = response.as(User.class);

        BaseAssertion.checkResponse(response, 404);
        UserAssertion.checkInvalidUser(user);
    }

    @DataProvider
    private Object[] getInvalidUsernames() {
        return new Object[]{"Tarantino", "5656", " "};
    }

    @Test
    public void updateByEmptyUsername() {
        final User user = userBuilder
                .setUserName("Malina")
                .setFirstName("Taras")
                .setLastName("Malinovich")
                .setEmail("tarasmalynovskyy@gmail.com")
                .setPassword("!Qwerty123")
                .setPhone("+3806333333")
                .build();

        final Response response = userClient.updateByUsername("", user);

        BaseAssertion.checkResponse(response, Status.FAILURE);
    }

    @Test
    public void updateByInvalidUsername() {
        final User user = userBuilder
                .setUserName("Malina")
                .setFirstName("Taras")
                .setLastName("Malinovich")
                .setEmail("tarasmalynovskyy@gmail.com")
                .setPassword("!Qwerty123")
                .setPhone("+3806333333")
                .build();

        final Response response = userClient.updateByUsername("Orevo", user);

        BaseAssertion.checkResponse(response, Status.SUCCESS);
    }
}
