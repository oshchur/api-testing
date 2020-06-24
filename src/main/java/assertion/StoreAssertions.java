package assertion;

import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class StoreAssertions {

    public static void assertStatus200(Response response){
        Assert.assertTrue(response.getStatusCode() == 200);
    }

    public static void assertServerError(APIResponse apiResp){
        Assert.assertEquals(apiResp.getMessage(), "something bad happened");
        Assert.assertEquals(apiResp.getType(), "unknown");
    }

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


}
