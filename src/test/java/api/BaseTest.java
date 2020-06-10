package api;

import client.StoreClient;
import io.restassured.response.ResponseBody;
import model.Store;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class BaseTest {

//    private static final String baseUri = "https://petstore.swagger.io/v2/pet/";
//
//    @Test
//    public void basicTest() {
//        Response response = given()
//                .baseUri(baseUri)
//                .queryParam("status", "available")
//                .get("findByStatus");
//        //.then().log().all().extract().response();
//        Assert.assertEquals(response.getStatusCode(), 200, "Error - incorrect status code");
//    }

    @Test
    public void liubaTest(){
        Store testObj = new Store(12, 35, 22, "2020-06-10T14:00:28.542Z", "placed", true);
        StoreClient sC = new StoreClient();

        JSONObject myObject = new JSONObject(sC.placeOrder(testObj).getBody().asString());
        String str = testObj.toEntity(myObject).toString();
        System.out.println(str);
    }
}
