package api.user;

import client.UserClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserClientTest {
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
