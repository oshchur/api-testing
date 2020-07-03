package api.storeTests;

import assertion.BaseAssertion;
import assertion.StoreAssertions;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class DeleteOrder {


    private Store store;
    StoreBuilder builder;

    @BeforeMethod
    public void createStore() {
        builder = new StoreBuilder();
        store = builder.setId("3")
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542+0000")
                .setStatus("placed")
                .setComplete("true")
                .build();

    }

    @Test
    public void deleteValidOrder() {

        StoreClient sC = new StoreClient();

        sC.placeOrder(store);
        Response response = sC.deleteOrderById(store.getId());

        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);
        APIResponse apiResponse = response.as(APIResponse.class);
        StoreAssertions.assertDeletedMessage(apiResponse, store.getId());

    }

    @Test
    public void deleteAndLookFor() {

        StoreClient sC = new StoreClient();

        sC.placeOrder(store);
        sC.deleteOrderById(store.getId());
        Response response = sC.getOrderById(store.getId());

        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_NOT_FOUND);
        APIResponse apiResponse = response.as(APIResponse.class);
        StoreAssertions.assertOrderNotFoundError(apiResponse);


    }

    @Test
    public void deleteNonExistOrder() {

        StoreClient sC = new StoreClient();

        sC.placeOrder(store);
        sC.deleteOrderById(store.getId());
        Response response = sC.deleteOrderById(store.getId());

        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_NOT_FOUND);
        APIResponse apiResponse = response.as(APIResponse.class);
        StoreAssertions.assertOrderNotFoundUnknown(apiResponse);
    }

    @Test(dataProvider = "invalidInput")
    public void deleteInvalidOrder(String id) {

        StoreClient sC = new StoreClient();

        sC.placeOrder(store);
        Response response = sC.getOrderById(id);

        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_NOT_FOUND);
        APIResponse apiResponse = response.as(APIResponse.class);
        StoreAssertions.assertNumberNotFoundExcept(apiResponse, id);

    }

    @DataProvider
    public Object[] invalidInput() {

        return new Object[]{"re", "12345678912345678912", "*/$"};
    }


}
