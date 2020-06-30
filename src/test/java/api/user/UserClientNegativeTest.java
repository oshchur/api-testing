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

public class UserClientNegativeTest {
    UserClient userClient;
    UserBuilder userBuilder;
    User user;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userBuilder = new UserBuilder();
    }

    @Test(dataProvider = "getInvalidUsernames")
    public void getUserByInvalidUsername(final String username) {
        final Response response = userClient.getUserByUsername(username);
        final User user = response.as(User.class);

        UserAssertion.checkInvalidUser(user);
    }

    @DataProvider
    private Object[] getInvalidUsernames() {
        return new Object[]{"Tarantino", "5656", " ", ""};
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

        BaseAssertion.checkResponse(response, 404);
    }

    @Test
    public void createTest() {
        user = userBuilder.constructRandomValidUser();
        userClient.create(user);
    }

    //unfortunatelly swagger allow login() before create()
    @Test(dependsOnMethods = "createTest")
    public void tryLoginAfterDelete() {
        userClient.delete(user.getUsername());
        new BaseAssertion(userClient.login(user.getUsername(), user.getPassword()))
                .checkResponse(400);
    }


}
