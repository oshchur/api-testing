package api;

import assertion.BaseAssertion;
import builders.UserBuilderImpl;
import builders.UserDirector;
import client.UserClient;
import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserClientTest {
    UserClient userClient;
    UserBuilderImpl userBuilderImpl;
    UserDirector userDirector;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userBuilderImpl = new UserBuilderImpl();
        userDirector = new UserDirector();

        userDirector.constructRandomUser(userBuilderImpl);
    }

    @Test
    public void createWithList() {
        Response response = userClient.createWithList(userBuilderImpl.getListResult(3));
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
        Response response = userClient.updateByUsername("Taras", userBuilderImpl.getResult());
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


    @Test
    public void create() {
//        System.out.println(new User(12, "q","w","e","r","r","b", 34).getJsonStr());
//        System.out.println(new User(12, "q","w","e","r","r","b", 34).toString());
        Response response = new UserClient().create(new User(12, "q", "w", "e", "r", "r", "b", 34));
        Assert.assertEquals(response.statusCode(), 200);
    }

}
