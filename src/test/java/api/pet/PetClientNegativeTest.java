package api.pet;

import assertion.BaseAssertion;
import assertion.PetAssertion;
import builders.PetBuilder;
import client.PetClient;
import io.restassured.response.Response;
import model.Pet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class PetClientNegativeTest {
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
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_NOT_FOUND);

    }

    @Test
    public void updateEmptyPet() {
        PetBuilder builder = new PetBuilder();
        Pet pet = builder.setId("")
                .setId("")
                .setStatus("")
                .build();
        PetClient petClient = new PetClient();
        Response response = petClient.createPet(pet);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_BAD_REQUEST);
    }

}