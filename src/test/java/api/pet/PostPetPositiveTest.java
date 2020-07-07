package api.pet;

import assertion.BaseAssertion;
import builders.PetBuilder;
import client.PetClient;
import io.restassured.response.Response;
import model.Pet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class PostPetPositiveTest {
    PetClient petClient;
    PetBuilder petBuilder;

    @BeforeClass
    public void beforeClass() {
        petClient = new PetClient();
        petBuilder = new PetBuilder();
    }

    @Test
    public void createPet() {
        PetBuilder builder = new PetBuilder();
        Pet pet = builder.setId("110")
                .setId("Jack")
                .setStatus("available")
                .build();
        PetClient petClient = new PetClient();
        Response response = petClient.createPet(pet);
        BaseAssertion.assertStatus(response, HttpURLConnection.HTTP_OK);

    }


}
