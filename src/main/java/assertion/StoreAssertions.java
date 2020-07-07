package assertion;

import model.APIResponse;
import model.Store;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

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
        Assert.assertEquals(apiResp.getType(), "unknown");
        Assert.assertEquals(apiResp.getMessage(), id);

    }

    public static void assertOrderNotFoundError(APIResponse apiResp){
        Assert.assertEquals(apiResp.getCode(), "1");
        Assert.assertEquals(apiResp.getType(), "error");
        Assert.assertEquals(apiResp.getMessage(), "Order not found");

    }

    public static void assertOrderNotFoundUnknown(APIResponse apiResp){
        Assert.assertEquals(apiResp.getCode(), "404");
        Assert.assertEquals(apiResp.getType(), "unknown");
        Assert.assertEquals(apiResp.getMessage(), "Order Not Found");

    }

    public static void assertNumberNotFoundExcept(APIResponse apiResp, String id){
        Assert.assertEquals(apiResp.getCode(), "404");
        Assert.assertEquals(apiResp.getType(), "unknown");
        Assert.assertEquals(apiResp.getMessage(), "java.lang.NumberFormatException: For input string: \""+ id + "\"");

    }

}
