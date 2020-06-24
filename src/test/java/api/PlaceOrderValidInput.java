package api;

import assertion.StoreAssertions;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.Store;
import org.testng.annotations.Test;

public class PlaceOrderValidInput {

    @Test
    public void validPropertiesOrder() {
        System.out.println("validPropertiesOrder");
        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId("13")
                .setPetId("33")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542+0000")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();

        Response response = sC.placeOrder(store);

        StoreAssertions.assertStatus200(response);
        Store apiResp = response.getBody().as(Store.class);
        StoreAssertions.assertPropertiesSet(apiResp, store);

    }
}
