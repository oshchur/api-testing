package api.storeTests;

import builders.StoreBuilder;
import client.StoreClient;
import io.restassured.response.Response;
import model.Store;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItem;

public class GetInventoryTest {

    private Store store;
    StoreBuilder builder;

    @BeforeMethod(alwaysRun = true)
    public void createStore() {
        builder = new StoreBuilder();
        store = builder.setId("4")
                .setPetId("3")
                .setQuantity("1")
                .setShipDate("2020-06-10T14:00:28.542+0000")
                .setStatus("placed")
                .setComplete("true")
                .build();

    }

    @Test(dataProvider = "statusAnyInput")
    public void findCustomStatusOrder(String status) {

        store.setStatus(status);

        StoreClient sC = new StoreClient();
        sC.placeOrder(store);
        Response response = sC.getInventory();

        response.then().body("status", hasItem(status));

    }

    @DataProvider
    public Object[] statusAnyInput() {

        return new Object[]{"liuba's order", "maPet", "mein Haustier"};
    }
}
