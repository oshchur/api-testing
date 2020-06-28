package assertion;

import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;

public class StoreAssertions {

       public static void assertPropertiesSet(Store apiResp, Store store){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(apiResp.getId(), store.getId());
        softAssert.assertEquals(apiResp.getPetId(), store.getPetId());
        softAssert.assertEquals(apiResp.getQuantity(), store.getQuantity());
        softAssert.assertEquals(apiResp.getShipDate(), store.getShipDate());
        softAssert.assertEquals(apiResp.getStatus(), store.getStatus());
        softAssert.assertEquals(apiResp.getComplete(), store.getComplete());
        softAssert.assertAll();

    }
    public static void assertDeletedMessage(APIResponse apiResp,String id){
        Assert.assertEquals(apiResp.getMessage(), id);
        Assert.assertEquals(apiResp.getType(), "unknown");
    }


}
