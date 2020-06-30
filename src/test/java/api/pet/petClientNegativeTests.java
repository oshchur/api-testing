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

public class petClientNegativeTests {
    private static final Logger log = Logger.getLogger(petClientPositiveTests.class);
    PetClient petClient;
    PetBuilder petBuilder;

    @BeforeClass
    public void beforeClass() {
        petClient = new PetClient();
        petBuilder = new PetBuilder();
    }

    @Test
    public void getInvalidPet() {
        Response response = petClient.getPetById("241210");
        Pet pet = response.as(Pet.class);
        PetAssertion.checkInvalidPet(pet);
    }

    @Test
    public void deletePet() {
        Response response = petClient.deletePetById("234234234");
        BaseAssertion.checkResponse(response, 404);

    }


    @Test
    public void builderTest() {
        Pet pet = petBuilder
                .setName("Jack")
                .setId("123123123123")
                .setStatus("iddqd")
                .build();
        log.info(pet);
    }
}