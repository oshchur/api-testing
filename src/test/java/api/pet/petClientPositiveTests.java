package api.pet;

import assertion.BaseAssertion;
import assertion.PetAssertion;
import builders.PetBuilder;
import client.PetClient;
import io.restassured.response.Response;
import model.Pet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class petClientPositiveTests {
    private static final Logger log = Logger.getLogger(petClientPositiveTests.class);

    PetClient petClient;
    PetBuilder petBuilder;


    @BeforeClass
    public void beforeClass() {
        petClient = new PetClient();
        petBuilder = new PetBuilder();
    }


    @Test
    public void getPetById() {
        Response response = petClient.getPetById("5");
        Pet pet = response.as(Pet.class);
        BaseAssertion.checkResponse(response, 200);
        PetAssertion.checkValidPet(pet);
    }

    @Test
    public void getPetByStatus() {
        Response response = petClient.getPetByStatus("sold");
        BaseAssertion.checkResponse(response, 200);

    }


    @Test
    public void deletePetById() {

        Pet pet = new Pet();
        PetClient petClient = new PetClient();
        pet.setId("5");
        String id = pet.getId();
        String str = petClient.deletePetById(id).getBody().asString();
        log.info(str);
    }
}
