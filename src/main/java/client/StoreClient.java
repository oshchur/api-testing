package client;

import io.restassured.response.Response;
import model.Store;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;


public class StoreClient extends BaseClient {
    private final String orderPath = "/store/order";
    private final String orderByIdPath = "/store/order/";
    private final String inventoryPath = "/store/inventory";

    public Response placeOrder(Store store) {
        return given(baseRequestSpecification)
                .body(store.toJson())
                .post(orderPath);
    }

    public Response getOrderById(String id) {
        return given(baseRequestSpecification)
                .queryParam("orderId", id)
                .get(orderByIdPath + id);
    }

    public Response deleteOrderById(String id) {
        return given(baseRequestSpecification)
                .queryParam("orderId", id)
                .delete(orderByIdPath + id);
    }

    public Response getInventory() {
        return given(baseRequestSpecification)
                .get(inventoryPath);
    }

}
