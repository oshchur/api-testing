package assertion;

import io.restassured.response.Response;
import model.Pet;
import org.testng.asserts.SoftAssert;

public class PetAssertion {

    public static void checkValidPet(Pet pet, Response response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Successful");
        softAssert.assertEquals(response.getContentType(), "application/json", "Successful");
        softAssert.assertEquals(pet.getId(), "", "Your pet id ");
        softAssert.assertEquals(pet.getStatus(), "sold", "");
        softAssert.assertAll();
    }

    public static void checkInvalidPet(Pet pet, Response response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 404, "Pet not found");
        softAssert.assertEquals(response.getContentType(), "application/json", "Not equals");
        softAssert.assertEquals(pet.getName(), "Kitty", "Pet not found");
        softAssert.assertAll();
    }

}
