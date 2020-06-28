package api.storeTests;

import assertion.BaseAssertion;
import assertion.StoreAssertions;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class PlaceOrderInvalidInput {

    @Test(dataProvider = "invalidInput")
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
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_INTERNAL_ERROR);

        APIResponse apiResp = response.getBody().as(APIResponse.class);
        BaseAssertion.assertServerError(apiResp);


    }

    @Test(dataProvider = "invalidInput")
    public void invalidPetIdOrder(String petId) {
        System.out.println("invalidPetId");
        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId("3")
                .setPetId(petId)
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_INTERNAL_ERROR);

        APIResponse apiResp = response.getBody().as(APIResponse.class);
        BaseAssertion.assertServerError(apiResp);

    }


    @Test(dataProvider = "invalidInput")
    public void invalidQuantityOrder(String quantity) {
        System.out.println("invalidQuantity");
        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId("3")
                .setPetId("3")
                .setQuantity(quantity)
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_INTERNAL_ERROR);

        APIResponse apiResp = response.getBody().as(APIResponse.class);
        BaseAssertion.assertServerError(apiResp);

    }


    @Test (dataProvider = "invalidInput")
    public void invalidCompleteOrder(String complete) {
        System.out.println("invalidComplete");
        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId("3")
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus("placed")
                .setComplete(complete)
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_INTERNAL_ERROR);

        APIResponse apiResp = response.getBody().as(APIResponse.class);
        BaseAssertion.assertServerError(apiResp);

    }

    @DataProvider
    public Object[] invalidInput() {

        return new Object []{"tг","12345678912345678912", "!@#"};
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
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_INTERNAL_ERROR);

        APIResponse apiResp = response.getBody().as(APIResponse.class);
        BaseAssertion.assertServerError(apiResp);
    }
    @DataProvider
    public Object[] invalidDateFormat() {

        return new Object[] {"2020\\06\\22", "22.09.2020", "22/09/2020", "22nd of July", "!@#"};
    }





}
