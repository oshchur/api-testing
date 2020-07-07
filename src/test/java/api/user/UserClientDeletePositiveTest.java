package api.user;

import assertion.BaseAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.net.HttpURLConnection;
import java.util.List;

public class UserClientDeletePositiveTest extends BaseUserClientTest {
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
        for(User user : users) {
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
}
