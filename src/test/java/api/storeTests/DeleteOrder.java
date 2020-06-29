package api.storeTests;

import assertion.BaseAssertion;
import assertion.StoreAssertions;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class DeleteOrder {

    @Test
    public void createAndDeleteOrder(){
        String id = "3";

        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId(id)
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542+0000")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        sC.placeOrder(store);

        Response response = sC.deleteOrderById(id);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);

        APIResponse apiResponse = response.as(APIResponse.class);
        StoreAssertions.assertDeletedMessage(apiResponse,id);

    }

    @Test
    public void createDeleteAndLookFor(){
        String id = "3";

        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId(id)
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542+0000")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        sC.placeOrder(store);

        sC.deleteOrderById(id);

        Response response = sC.getOrderById(id);

        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_NOT_FOUND);

        APIResponse apiResponse = response.as(APIResponse.class);
        StoreAssertions.assertOrderNotFoundError(apiResponse);

    }

    @Test
    public void deleteNonExistOrder(){
        String id = "3";

        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId(id)
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542+0000")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        sC.placeOrder(store);

        sC.deleteOrderById(id);

        Response response = sC.deleteOrderById(id);

        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_NOT_FOUND);
        APIResponse apiResponce = response.as(APIResponse.class);
        StoreAssertions.assertOrderNotFoundUnknown(apiResponce);
    }

    @Test(dataProvider = "invalidInput")
    public void deleteInvalidOrder(String id ){

        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId(id)
                .setPetId("1")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542+0000")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        sC.placeOrder(store);

        Response response = sC.getOrderById(id);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_NOT_FOUND);
        APIResponse apiResponce = response.as(APIResponse.class);
        StoreAssertions.assertNumberNotFoundExcept(apiResponce, id);

    }
    @DataProvider
    public Object[] invalidInput() {

        return new Object []{"re","12345678912345678912", "*/$"};
    }


}
