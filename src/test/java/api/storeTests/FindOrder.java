package api.storeTests;

import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Store;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItem;

public class FindOrder {

    @Test
    public void createAndFindOrder(){

        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId("3")
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542Z")
                .setStatus("placed")
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        sC.placeOrder(store);

        Response response = sC.getOrderById("3");
        Store apiResponse = response.as(Store.class);
        Assert.assertEquals(apiResponse.getId(),store.getId());
    }


    @Test(dataProvider = "createAndLookFor")
    public void createAndLookForInvalidVal(String idReqOne, String idReqTwo){

            StoreBuilder builder = new StoreBuilder();
            Store store = builder.setId(idReqOne)
                    .setPetId("3")
                    .setQuantity("1")
                    .setShipDate("2020-06-10T14:00:28.542Z")
                    .setStatus("placed")
                    .setComplete("true")
                    .build();
            System.out.println(builder.build().toString());

            StoreClient sC = new StoreClient();
            sC.placeOrder(store);

        Response response = sC.getOrderById(idReqTwo);
        Store apiResponse = response.as(Store.class);
        Assert.assertNotEquals(apiResponse.getId(),store.getId());
    }

    @DataProvider
    public Object[][] createAndLookFor() {

        Object [][] data = new Object [2][2];

        data[0][0] = "2";
        data[0][1] = "2.0";
        data[1][0] = "re";
        data[1][1] = "re";

        return data;
    }
}
