package api;

import assertion.BaseAssertion;
import client.UserClient;
import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserClientTest {
    //        TODO make by pattern builder
    final UserClient userClient = new UserClient();
    final List<User> randomUsers = userClient.createRandomUsers();

    @Test
    public void createWithList() {
        Response response = userClient.createWithList(randomUsers);
        response.getBody().prettyPrint();

        BaseAssertion.checkResponse(response);
    }

    @Test
    public void updateByUsername() {
        Response response = userClient.updateByUsername("Taras", randomUsers.get(1));
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
