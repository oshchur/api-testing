package api.user;

import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
}
