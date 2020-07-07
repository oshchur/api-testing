package api.storeTests;

import assertion.StoreAssertions;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FindOrder {

    private Store store;
    StoreBuilder builder;

    @BeforeMethod(alwaysRun = true)
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
    public void lookForExistingOrder() {

        StoreClient sC = new StoreClient();
        sC.placeOrder(store);
        Response response = sC.getOrderById(store.getId());
        Store apiResponse = response.as(Store.class);
        Assert.assertEquals(apiResponse.getId(), store.getId());
    }


    @Test(dataProvider = "lookWithInvalidValues")
    public void lookWithInvalidValues(String id) {

        StoreClient sC = new StoreClient();
        Response response = sC.getOrderById(id);
        APIResponse apiResponse = response.as(APIResponse.class);
        StoreAssertions.assertNumberNotFoundExcept(apiResponse, id);
    }

    @DataProvider
    public Object[] lookWithInvalidValues() {

        return new Object[]{"2.0", "re"};

    }
}
