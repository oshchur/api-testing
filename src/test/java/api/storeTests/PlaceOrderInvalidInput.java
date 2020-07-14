package api.storeTests;

import assertion.BaseAssertion;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class PlaceOrderInvalidInput {

    private Store store;
    StoreBuilder builder;

    @BeforeMethod(alwaysRun = true)
    public void createStore() {
        builder = new StoreBuilder();
        store = builder.setId("5")
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542+0000")
                .setStatus("placed")
                .setComplete("true")
                .build();

    }

    @Test(dataProvider = "invalidInput")
    public void invalidIdOrder(String id) {

        store.setId(id);
        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_INTERNAL_ERROR);
        APIResponse apiResp = response.getBody().as(APIResponse.class);
        BaseAssertion.assertServerError(apiResp);


    }

    @Test(dataProvider = "invalidInput")
    public void invalidPetIdOrder(String petId) {

        store.setId(petId);
        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_INTERNAL_ERROR);
        APIResponse apiResp = response.getBody().as(APIResponse.class);
        BaseAssertion.assertServerError(apiResp);

    }


    @Test(dataProvider = "invalidInput")
    public void invalidQuantityOrder(String quantity) {

        store.setQuantity(quantity);
        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_INTERNAL_ERROR);
        APIResponse apiResp = response.getBody().as(APIResponse.class);
        BaseAssertion.assertServerError(apiResp);

    }


    @Test(dataProvider = "invalidInput")
    public void invalidCompleteOrder(String complete) {

        store.setComplete(complete);
        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_INTERNAL_ERROR);
        APIResponse apiResp = response.getBody().as(APIResponse.class);
        BaseAssertion.assertServerError(apiResp);

    }

    @DataProvider
    public Object[] invalidInput() {

        return new Object[]{"tг", "12345678912345678912", "!@#", "6.0"};
    }

    @Test(dataProvider = "invalidDateFormat")
    public void invalidDateFormatOrder(String shipDate) {

        store.setShipDate(shipDate);
        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_INTERNAL_ERROR);
        APIResponse apiResp = response.getBody().as(APIResponse.class);
        BaseAssertion.assertServerError(apiResp);
    }

    @DataProvider
    public Object[] invalidDateFormat() {

        return new Object[]{"2020\\06\\22", "22.09.2020", "22/09/2020", "22nd of July", "!@#"};
    }

}
