package api;

import builders.StoreBuilderWithDirector.StoreDirector;
import builders.StoreBuilderWithDirector.StoreInvalidParam;
import builders.StoreBuilderWithDirector.StoreValidParam;
import client.StoreClient;
import model.Store;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class StoreTests {

    @Test
    public void validBuilderTest() {
        StoreValidParam valPar = new StoreValidParam();
        StoreDirector crSt = new StoreDirector();
        crSt.setStoreBuilder(valPar);
        crSt.constructStore();
        Store store = crSt.getStore();
        System.out.println(store.toString());
    }

    @Test
    public void invalidBuilderTest() {
        StoreInvalidParam invalPar = new StoreInvalidParam();
        StoreDirector crSt = new StoreDirector();
        crSt.setStoreBuilder(invalPar);
        crSt.constructStore();
        Store store = crSt.getStore();
        System.out.println(store.toString());
    }

    @Test
    public void getInventoryTest() {
        System.out.println("getInventoryTest");
        Store testObj = new Store();
        StoreClient sC = new StoreClient();
        String id = testObj.getId();
        String str = sC.getInventory().getBody().asString();
        System.out.println(str);
    }

    @Test
    public void postOrderTest() {
        System.out.println("postOrderTest");
        Store testObj = new Store("13", "33", "23", "2020-06-10T14:00:28.542Z", "placed", "true");
        StoreClient sC = new StoreClient();

        JSONObject myObject = new JSONObject(sC.placeOrder(testObj).getBody().asString());
        String str = testObj.toEntity(myObject).toString();
        System.out.println(str);
    }

    @Test
    public void getOrderByIdTest() {
        System.out.println("getOrderByIdTest");
        Store testObj = new Store();
        testObj.setId("1");
        StoreClient sC = new StoreClient();
        String id = testObj.getId();
        String str = sC.getOrderById(id).getBody().asString();
        System.out.println(str);
    }

    @Test
    public void deleteOrderByIdTest() {
        System.out.println("deleteOrderByIdTest");
        Store testObj = new Store();
        testObj.setId("1");
        StoreClient sC = new StoreClient();
        String id = testObj.getId();
        String str = sC.deleteOrderById(id).getBody().asString();
        System.out.println(str);
    }
}
