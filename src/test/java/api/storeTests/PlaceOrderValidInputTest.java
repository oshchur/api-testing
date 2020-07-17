package api.storeTests;

import assertion.BaseAssertion;
import assertion.StoreAssertions;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.Store;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class PlaceOrderValidInputTest {

    private Store store;
    StoreBuilder builder;

    @BeforeMethod(alwaysRun = true)
    public void createStore() {
        builder = new StoreBuilder();
        store = builder.setId("2")
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542+0000")
                .setStatus("placed")
                .setComplete("true")
                .build();

    }

    @Test
    public void validPropertiesOrder() {

        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);
        Store apiResp = response.getBody().as(Store.class);
        StoreAssertions.assertPropertiesSet(apiResp, store);

    }

    @Test
    public void nullPropertiesOrder() {

        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId(null)
                .setPetId(null)
                .setQuantity(null)
                .setShipDate(null)
                .setStatus(null)
                .setComplete(null)
                .build();

        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);

        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);

    }


    @Test
    public void shipHourNotSet() {

        store.setShipDate("2020-06-10");
        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);
        Store apiResp = response.getBody().as(Store.class);
        Assert.assertEquals(apiResp.getShipDate(), "2020-06-10T00:00:00.000+0000");

    }


    @Test(dataProvider = "statusAnyInput")
    public void statusAnyInputOrder(String status) {

        store.setStatus(status);
        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);


    }

    @DataProvider
    public Object[] statusAnyInput() {

        return new Object[]{"tг", "12345678912345678912", "!@#"};
    }

    @Test(dataProvider = "completeValidInputs")
    public void completeValidInputs(String complete) {

        store.setComplete(complete);
        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);
        Store apiResp = response.getBody().as(Store.class);
        StoreAssertions.assertPropertiesSet(apiResp, store);

    }

    @DataProvider
    public Object[] completeValidInputs() {

        return new Object[]{"true", "false"};
    }


}
