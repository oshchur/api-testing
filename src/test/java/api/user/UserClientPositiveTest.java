package api.user;

import assertion.BaseAssertion;
import assertion.LogResponse;
import assertion.UserAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class UserClientPositiveTest {
    UserClient userClient;
    UserBuilder userBuilder;
    User user;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userBuilder = new UserBuilder();
    }

    @Test
    public void createWithList() {
        final List<User> randomUsers = userBuilder.constructRandomListValidUsers(3);
        final Response response = userClient.createWithList(randomUsers);

        BaseAssertion.checkResponse(response, 200);
    }

    @Test
    public void getUserByUsername() {
        final Response response = userClient.getUserByUsername("Malina");
        final User user = response.as(User.class);

        BaseAssertion.checkResponse(response, 200);
        UserAssertion.checkValidUser(user);
    }

    @Test
    public void updateUserByUsername() {
        final User user = userBuilder
                .setUserName("Malina")
                .setFirstName("Taras")
                .setLastName("Malynovskyi")
                .setEmail("tarasmalynovskyy@gmail.com")
                .setPassword("!Qwerty123")
                .setPhone("+3806333333")
                .setUserStatus(0)
                .build();

        Response response = userClient.updateByUsername("Malina", user);
        BaseAssertion.checkResponse(response, 200);
    }

    @Test
    public void createTest() {
        user = userBuilder.constructRandomValidUser();
        new BaseAssertion(userClient.create(user))
                .checkResponse(200);
    }

    @Test(dependsOnMethods = "createTest")
    public void loginTest() {
        new BaseAssertion(userClient.login(user.getUsername(), user.getPassword()))
                .checkResponse(200);
    }


    @Test(dependsOnMethods = "createTest")
    public void logoutTest() {
        loginTest();
        new BaseAssertion(userClient.logout())
                .checkResponse(200);
    }

    @Test(dependsOnMethods = "createTest")
    public void deleteTest() {
        new BaseAssertion(userClient.delete(user.getUsername()))
                .checkResponse(200);
    }
}
