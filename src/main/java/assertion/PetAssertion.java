package assertion;

import io.restassured.response.Response;
import model.Pet;
import org.testng.asserts.SoftAssert;

public class PetAssertion {

    public static void checkValidPet(Pet pet) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(pet.getName(), "Otussie", "Pet name is :");
        softAssert.assertEquals(pet.getId(), "241210", "Your pet id ");
        softAssert.assertEquals(pet.getStatus(), 200, "Pet is ");
        softAssert.assertAll();
    }

    public static void checkInvalidPet(Pet pet) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(pet.getName(), null,"Error:Not found" );
        softAssert.assertEquals(pet.getId(), null, "Error:Not found");
        softAssert.assertEquals(pet.getStatus(),null, "Pet not found");
        softAssert.assertAll();
    }

}
