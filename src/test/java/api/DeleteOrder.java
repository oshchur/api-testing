package api;

import assertion.BaseAssertion;
import assertion.StoreAssertions;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class DeleteOrder {
    ///create,delete and find

    @Test
    public void createAndDeleteOrder(){
        String id = "3";

        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId(id)
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        sC.placeOrder(store);

        Response response = sC.deleteOrderById(id);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);

        APIResponse apiResponse = response.as(APIResponse.class);
        StoreAssertions.assertDeletedMessage(apiResponse,id);

    }


    @Test
    public void deleteNonExistOrder(){

        StoreClient sC = new StoreClient();
        Response response2 = sC.getOrderById("111");
        Store apiResponce = response2.as(Store.class);
        System.out.println(response2.getStatusCode());
        response2.prettyPrint();
        //404
        //"code": 1,
        //    "type": "error",
        //    "message": "Order not found"
    }

    @Test
    public void deleteInvalidOrder(){
        String id = "re";

        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId(id)
                .setPetId("1")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);

        Response response2 = sC.getOrderById(id);
        Store apiResponce = response2.as(Store.class);
        System.out.println(response2.getStatusCode());
        response2.prettyPrint();
//        //404
//        "code": 404,
//                "type": "unknown",
//                "message": "java.lang.NumberFormatException: For input string: \"re\""
    }


}
