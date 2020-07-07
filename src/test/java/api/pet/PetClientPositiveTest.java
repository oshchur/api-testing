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

public class PetClientPositiveTest {


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
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
        PetAssertion.checkValidPet(pet);
    }

    @Test
    public void getPetByStatus() {
        Response response = petClient.getPetByStatus("sold");
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);

    }

    @Test
    public void deletePetById() {

        Pet pet = new PetBuilder().build();
        petClient.createPet(pet);
        Response response = petClient.deletePetById(pet.getId());
        BaseAssertion.checkResponse(response, HttpURLConnection.HTTP_OK);
    }
}
