package assertion;

import io.restassured.response.Response;
import model.APIResponse;
import model.Store;
import org.testng.Assert;

public class StoreAssertions {

    public static void assertStatus200(Response response){
        Assert.assertTrue(response.getStatusCode() == 200);
    }

    public static void assertServerError(Response response){
        Assert.assertTrue(response.getStatusCode() == 500);

        APIResponse apiResp = response.getBody().as(APIResponse.class);
        Assert.assertEquals(apiResp.getMessage(), "something bad happened");
        Assert.assertEquals(apiResp.getType(), "unknown");
    }

    public static void assertPropertiesSet(Response response, Store store){
        Store apiResp = response.getBody().as(Store.class);
        Assert.assertEquals(apiResp.getId(), store.getId());
        Assert.assertEquals(apiResp.getPetId(), store.getPetId());
        Assert.assertEquals(apiResp.getQuantity(), store.getQuantity());
        Assert.assertEquals(apiResp.getShipDate(), store.getShipDate());
        Assert.assertEquals(apiResp.getStatus(), store.getStatus());
        Assert.assertEquals(apiResp.getComplete(), store.getComplete());

    }

}
