package api;

import builders.PetBuilder;
import client.PetClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetsTest {
    PetClient petClient;
    PetBuilder petBuilder;

    @BeforeClass
    public void beforeClass() {
        petClient = new PetClient();
        petBuilder = new PetBuilder();
    }

    @Test
    public void getInvalidPet() {
        Response response = petClient.getPetById("4");

        Assert.assertEquals(response.getStatusCode(), 404, "Pet not found");

    }

    @Test
    public void getValidPet() {
        Response response = petClient.getPetById("5");
        Assert.assertEquals(response.getStatusCode(), 200, "Found pet");

    }

    @Test
    public void BuilderTest() {
        PetBuilder petBuilder = new PetBuilder();
        petBuilder.setId("5")
                .setName("Jack")
                .setStatus("Sold")
                .build();
        System.out.println(petBuilder.build());
    }


}
