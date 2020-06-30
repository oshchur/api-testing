package api.storeTests;

import assertion.BaseAssertion;
import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.Store;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.hasItem;

public class GetInventory {

    @Test(dataProvider = "statusAnyInput")
    public void findCustomStatusOrder(String status) {
        System.out.println("statusAnyInput");
        StoreBuilder builder = new StoreBuilder();
        Store store = builder.setId("3")
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542+0000")
                .setStatus(status)
                .setComplete("true")
                .build();
        System.out.println(builder.build().toString());

        StoreClient sC = new StoreClient();
        sC.placeOrder(store);
        Response response = sC.getInventory();
        response.prettyPrint();
       response.then().body("status", hasItem(status));

    }

    @DataProvider
    public Object[] statusAnyInput() {

        return new Object []{"liuba's order","maPet", "mein Haustier"};
    }
}
