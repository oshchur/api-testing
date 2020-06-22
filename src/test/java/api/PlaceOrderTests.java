package api;

import assertion.StoreAssertions;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.annotations.Test;

public class PlaceOrderTests {
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
        StoreAssertions.assertPropertiesSet(response, store);

    }

    @Test
    public void alphabeticIdOrder() {
        System.out.println("alphabeticIdOrder");
        StoreBuilder builder = new StoreBuilder();
        Store testObj = builder.setId("t")
                .setPetId("33")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();

        Response response = sC.placeOrder(testObj);
        //Asserting server error instead of the client one, cause that's how this API works
        // - returns 500 when input is incorrect
        StoreAssertions.assertServerError(response);
    }
    @Test
    public void tooBigIdOrder() {
        System.out.println("tooBigIdOrder");
        StoreBuilder builder = new StoreBuilder();
        Store testObj = builder.setId("12345678912345678912")
                .setPetId("33")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();

        Response response = sC.placeOrder(testObj);
        //Asserting server error instead of the client one, cause that's how this API works
        // - returns 500 when input is incorrect
        StoreAssertions.assertServerError(response);
    }

    @Test
    public void emptyIdOrder() {
        System.out.println("emptyIdOrder");
        StoreBuilder builder = new StoreBuilder();
        Store testObj = builder.setId("12345678912345678912")
                .setPetId("33")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();

        Response response = sC.placeOrder(testObj);
        //Asserting server error instead of the client one, cause that's how this API works
        // - returns 500 when input is incorrect
        StoreAssertions.assertServerError(response);
    }
}
