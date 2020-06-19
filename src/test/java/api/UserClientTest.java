package api;

import assertion.BaseAssertion;
import builders.UserBuilder;
import client.UserClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
    public void getUserByUsername() {
        Response response = userClient.getUserByUserName("Taras");
        response.getBody().prettyPrint();

        BaseAssertion.checkResponse(response);
    }

    @Test
    public void updateByUsername() {
        Response response = userClient.updateByUsername("Taras", userBuilder.constructRandomUser());
        response.getBody().prettyPrint();

        BaseAssertion.checkResponse(response);
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
