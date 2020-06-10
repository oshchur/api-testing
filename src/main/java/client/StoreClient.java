package client;

import io.restassured.response.Response;


public class StoreClient extends BaseClient{

    Response placeOrder(String status) {
       return reqSpec.given()
                }
   Response getOrderById(String id) {}
   Response deleteOrderById(String id) {}
   Response getInventory() {}
}
