package api.user;

import assertion.BaseAssertion;
import assertion.UserAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import listeners.Listener;
import model.User;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.List;

@Listeners(Listener.class)
public class UserClientTest {
    UserClient userClient;
    UserBuilder userBuilder;
    User expectedUser;

    @BeforeSuite
    public void suiteSettings(ITestContext context) {
        TestRunner testRunner = (TestRunner) context;
        ((TestRunner) context).setOutputDirectory("src/logs/ok/output.txt");
    }

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
        final Response createUserResponse = userClient.create(expectedUser);
        final Response getUserByUsernameResponse = userClient.getUserByUsername("Malina");
        final User user = getUserByUsernameResponse.as(User.class);

        BaseAssertion.checkResponse(createUserResponse, HttpURLConnection.HTTP_OK);
        BaseAssertion.checkResponse(getUserByUsernameResponse, HttpURLConnection.HTTP_OK);
        UserAssertion.checkValidUser(user, expectedUser);
    }

    @Test
    public void updateUserByUsername() {
        final User user = userClient.getUserByUsername("Malina").as(User.class);
        user.setLastName("Malynovskyi");

        Response response = userClient.updateByUsername("Malina", user);

        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }


    @Test
    public void logoutTest() {
        Response response = userClient.logout();
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }

    @Test
    public void loginTest() {
        User user = new UserBuilder().constructRandomValidUser();
        Response response = userClient.login(user.getUsername(), user.getPassword());
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }

}