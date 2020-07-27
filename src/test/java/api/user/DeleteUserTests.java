package api.user;

import assertion.BaseAssertion;
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
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;
import java.util.List;

@Listeners(Listener.class)
public class DeleteUserTests {
    UserClient userClient;
    UserBuilder userBuilder;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userBuilder = new UserBuilder();
    }

    @Test(dataProvider = "users")
    public void createAndThenDeleteUser(User user) {
        userClient.create(user);
        Response response = userClient.delete(user.getUsername());
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }

    @DataProvider
    public Object[] users() {
        return userBuilder
                .constructRandomListValidUsers(4)
                .toArray();
    }

    @Test(dataProvider = "arrOfUsers")
    public void createUsersFromArrayAndThenDeleteEach(List<User> users) {
        userClient.createWithList(users);
        for (User user : users) {
            Response response = userClient.delete(user.getUsername());
            BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
        }
    }

    @DataProvider
    public Object[] arrOfUsers() {
        return new Object[]{
                userBuilder.constructRandomListValidUsers(4)
        };
    }

    @Test
    public void deleteUnExistingUser() {
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

    @Test
    public void deleteTest() {
        User user = new UserBuilder().constructRandomValidUser();
        userClient.create(user);
        Response response = userClient.delete(user.getUsername());
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }
}
