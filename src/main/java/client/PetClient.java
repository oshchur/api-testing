package client;

import io.restassured.response.Response;
import model.Pet;
import org.json.JSONArray;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetClient extends BaseClient {
    private final String petClientUrl = "/pet";
    private final String petIdUrl = petClientUrl + "​/{petId}";
    private final String petStatusUrl = petClientUrl + "/findByStatus";
    private final String deletePetUrl = petClientUrl + "/{petID}";

    public Response getPetByStatus(String status) {
        return given(baseRequestSpecification)
                .pathParam("status", status)
                .get(petStatusUrl);
    }

    public Response getPetById(Integer id) {
        return given(baseRequestSpecification).
                pathParam("petId", id).
                get(petIdUrl);
    }

    public Response deletePetById(Integer id) {
        return given(baseRequestSpecification).
                pathParam("petId", id).
                delete(deletePetUrl);
    }

    public Response createPet(Pet pets) {
        return given(baseRequestSpecification)
                .body(pets)
                .post(petClientUrl);
    }
}

