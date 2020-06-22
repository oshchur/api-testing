package api;

import assertion.BaseAssertion;
import assertion.UserAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserClientTest {
    UserClient userClient;
    UserBuilder userBuilder;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userBuilder = new UserBuilder();
    }

    @Test
    public void createWithList() {
        Response response = userClient.createWithList(userBuilder.constructRandomListUsers(3));
        response.getBody().prettyPrint();

        BaseAssertion.checkResponse(response);
    }

    @Test
    public void getUserByValidUsername() {
        final Response response = userClient.getUserByUserName("Malina");
        final User user = response.as(User.class);

        UserAssertion.checkValidUser(response, user);
    }

    @DataProvider
    private Object[] getInvalidUsernames() {
        return new Object[]{"Tarantino", "5656", " ", ""};
    }

    @Test(dataProvider = "getInvalidUsernames")
    public void getUserByInvalidUsername(final String username) {
        final Response response = userClient.getUserByUserName(username);
        final User user = response.as(User.class);

        UserAssertion.checkInvalidUser(response, user);
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

        BaseAssertion.checkNegativeResponse(response);
    }

    @Test
    public void login() {
        Assert.assertEquals(new UserClient().login("Nikita", "0089711").statusCode(), 200);
    }


    @Test
    public void logout() {
        Assert.assertEquals(new UserClient().logout().statusCode(), 200);
    }


//    @Test
//    public void create() {
////        System.out.println(new User(12, "q","w","e","r","r","b", 34).getJsonStr());
////        System.out.println(new User(12, "q","w","e","r","r","b", 34).toString());
//        Response response = new UserClient().create(new User(12, "q", "w", "e", "r", "r", "b", 34));
//        Assert.assertEquals(response.statusCode(), 200);
//    }

}
