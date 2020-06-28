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

        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);

        Store apiResp = response.getBody().as(Store.class);
        StoreAssertions.assertPropertiesSet(apiResp, store);

    }

//    @DataProvider
//    public Object[][] validProperties() {
//
//        Object [][] data = new Object [2][2];
//
//        data[0][0] = "2";
//        data[0][1] = "2.0";
//        data[1][0] = "re";
//        data[1][1] = "re";
//
//        return data;
//    }
    //add more valid inputs
    // with @DataProvider

    @Test
    public void nullPropertiesOrder() {
        System.out.println("nullPropertiesOrder");
        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId(null)
                .setPetId(null)
                .setQuantity(null)
                .setShipDate(null)
                .setStatus(null)
                .setComplete(null)
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);

        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);

    }

//can be deleted if test# 1 is improved with @DataProvider
    @Test(dataProvider = "statusAnyInput")
    public void statusAnyInputOrder(String status) {
        System.out.println("statusAnyInput");
        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId("3")
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus(status)
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        Response response = sC.placeOrder(store);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);


    }

    @DataProvider
    public Object[] statusAnyInput() {

        return new Object []{"tг","12345678912345678912", "!@#"};
    }



}
