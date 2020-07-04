package api.user;

import assertion.BaseAssertion;
import assertion.UserAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class UserClientNegativeTest {
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
    public void createByEmptyList() {
        Response response = userClient.createWithList(new ArrayList<>());

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
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
        return new Object[]{"Tarantino",
                "5656",
                " ",
                "флдж",
                "*/!@##!@%$!",
                "4322___vfdjkqWW",
                "     ",
                "  asd221342 4 324 ",};
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
}
