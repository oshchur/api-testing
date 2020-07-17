package api.user;

import assertion.UserAssertion;
import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class UserClientCreatePositiveTest extends BaseUserClientTest {

    @Test(dataProvider = "users")
    public void test(User user) {
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
    public void createUserGetHimAndCheck_version_2(User user) {
        userClient.create(user);
        Response response = userClient.getUserByUsername(user.getUsername());
        User returnUser = response.as(User.class);
        UserAssertion.checkValidUser(user, returnUser);
    }
    @DataProvider
    public Object[] getCreatingUsers() {
        List<User> users = userBuilder.constructRandomListValidUsers(4);
        for(User u : users) {
            userClient.create(u);
        }
        return  users.toArray();
    }

}
