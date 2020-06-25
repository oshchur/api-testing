package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Pet;

import static io.restassured.RestAssured.given;

public class PetClient extends BaseClient {
    private final String petClientUrl = "/pet";
    private final String petIdUrl = petClientUrl + "​/{petId}";
    private final String petStatusUrl = petClientUrl + "/findByStatus";
    private final String deletePetUrl = petClientUrl + "/{petID}";


    public Response getPetByStatus(String status) {
        return given(baseRequestSpecification(ContentType.JSON))
                .pathParam("status", status)
                .get(petStatusUrl);
    }

    public Response getPetById(String id) {
        return given(baseRequestSpecification(ContentType.JSON))
                .pathParam("petId", id)
                .get(petIdUrl);
    }

    public Response deletePetById(String id) {
        return given(baseRequestSpecification(ContentType.JSON))
                .pathParam("petId", id)
                .delete(deletePetUrl);
    }

    public Response createPet(Pet pets) {
        return given(baseRequestSpecification(ContentType.JSON))
                .body(pets)
                .post(petClientUrl);
    }

    public Response updatePet(Pet pets) {
        return given(baseRequestSpecification(ContentType.JSON))
                .body(pets)
                .post(petClientUrl);
    }

}

