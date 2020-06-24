package api;

import assertion.StoreAssertions;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PlaceOrderInvalidInput {

    @Test(dataProvider = "invalidId")
    public void invalidIdOrder(String id) {
        System.out.println("invalidIdOrder");
        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId(id)
                .setPetId("33")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();

        Response response = sC.placeOrder(store);
        //Asserting server error instead of the client one, cause that's how this API works
        // - returns 500 when input is incorrect
        APIResponse apiResp = response.getBody().as(APIResponse.class);
        StoreAssertions.assertServerError(apiResp);
    }
    @DataProvider
    public Object[] invalidId() {

        return new Object []{"t","12345678912345678912"}; //+ empty value
    }

    @Test(dataProvider = "invalidDateFormat")
    public void invalidDateFormatOrder(String date) {
        System.out.println("invalidDateFormatOrder");
        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId("1")
                .setPetId("1")
                .setQuantity("1")
                .setShipDate(date)
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();

        Response response = sC.placeOrder(store);
        //Asserting server error instead of the client one, cause that's how this API works
        // - returns 500 when input is incorrect
        APIResponse apiResp = response.getBody().as(APIResponse.class);
        StoreAssertions.assertServerError(apiResp);
    }
    @DataProvider
    public Object[] invalidDateFormat() {

        return new Object[] {"2020\\06\\22", "22.09.2020", "22/09/2020", "22nd of July"};
    }
}
