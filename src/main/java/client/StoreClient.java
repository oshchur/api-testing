package client;

import io.restassured.response.Response;
import model.Store;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;


public class StoreClient extends BaseClient {
    private final String storeBasePath = "/store";
    private final String orderPath = storeBasePath + "/order";
    private final String orderByIdPath = orderPath + "/{orderId}";
    private final String inventoryPath = storeBasePath +"/inventory";

    public Response placeOrder(Store store) {
        return given(baseRequestSpecification)
                            .body(store.toJson())
                .post(orderPath);
    }

    public Response getOrderById(String id) {
        return given(baseRequestSpecification)
                .pathParam("orderId", id)
                .get(orderByIdPath);
    }

    public Response deleteOrderById(String id) {
        return given(baseRequestSpecification)
                .pathParam("orderId", id)
                .delete(orderByIdPath);
    }

    public Response getInventory() {
        return given(baseRequestSpecification)
                .get(inventoryPath);
    }

}
