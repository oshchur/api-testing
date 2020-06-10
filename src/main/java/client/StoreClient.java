package client;

import io.restassured.response.Response;
import model.Store;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;


public class StoreClient extends BaseClient {
    private final String orderPath = "/store/order";
    private final String orderByIdPath = "/store/order/{orderId}";
    private final String inventoryPath = "/store/inventory";

    public Response placeOrder(Store store) {
        JSONObject jsonObject = toJson(store);

        return given(baseRequestSpecification)
                .body(jsonObject.toString())
                .post(orderPath);
    }

    public Response getOrderById(String id) {
        return given(baseRequestSpecification)
                .queryParam("orderId", id)
                .get(orderByIdPath);
    }

    public Response deleteOrderById(String id) {
        return given(baseRequestSpecification)
                .queryParam("orderId", id)
                .delete(orderByIdPath);
    }

    public Response getInventory() {
        return given(baseRequestSpecification)
                .get(inventoryPath);
    }

    private JSONObject toJson(Store store) {
        JSONObject object = new JSONObject();

        object.put("id", store.getId());
        object.put("petId", store.getPetId());
        object.put("quantity", store.getQuantity());
        object.put("shipDate", store.getShipDate());
        object.put("status", store.getStatus());
        object.put("complete", store.getComplete());

        return object;
    }
}
