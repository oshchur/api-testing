package api.user;

import assertion.BaseAssertion;
import assertion.UserAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import listeners.Listener;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class CreateUserTests {
    UserClient userClient;
    UserBuilder userBuilder;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userBuilder = new UserBuilder();
    }

    @Test
    public void createTest() {
        User user = new UserBuilder().constructRandomInvalidUser();
        System.out.println(user.toString());
        Response response = userClient.create(user);
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }

    @Test
    public void createWithList() {
        final List<User> randomUsers = userBuilder.constructRandomListValidUsers(3);
        final Response response = userClient.createWithList(randomUsers);

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }

    @Test
    public void createByEmptyList() {
        Response response = userClient.createWithList(new ArrayList<>());

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }

    @Test(dataProvider = "users")
    public void createRandomUser(User user) {
        Response response = userClient.create(user);

        Assert.assertEquals(response.jsonPath().getLong("message"), user.getId());
    }

    @DataProvider
    public Object[] users() {
        return userBuilder
                .constructRandomListValidUsers(4)
                .toArray();
    }
    
    @Test(dataProvider = "getCreatingUsers")
    public void createUserGetHimAndCheck(User user) {
        userClient.create(user);
        Response response = userClient.getUserByUsername(user.getUsername());
        User returnUser = response.as(User.class);
        UserAssertion.checkValidUser(user, returnUser);
    }

    @DataProvider
    public Object[] getCreatingUsers() {
        List<User> users = userBuilder.constructRandomListValidUsers(4);
        for (User u : users) {
            userClient.create(u);
        }
        return users.toArray();
    }
}
