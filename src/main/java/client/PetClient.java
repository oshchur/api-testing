package client;

import io.restassured.response.Response;
import model.Pet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetClient extends BaseClient {
    private final String petClientPath = "/pet";
    private final String petIdPath = "​/pet​/{petId}";
    private final String petStatusPath = "/pet/findByStatus";
    private final String deletePetPath = "/pet/{petID}";


    public Response getPetByStatus(String status) {
        return given()
                .queryParam("status", status)
                .get(petStatusPath);
    }


    public Response getPetById(Integer id) {
        return given(baseRequestSpecification).
                queryParam("petId", id).
                get(petIdPath);
    }

    public Response deletePetById(Integer id) {
        return given(baseRequestSpecification).
                queryParam("petId", id).
                delete(deletePetPath);
    }

    public Response createPet(List<Pet> pets) {
        JSONArray requestParams = new JSONArray();
        for (Pet pet : pets) {
            requestParams.put(petToJson(pet));
        }
        return given(baseRequestSpecification).body(requestParams.toString()).post(petClientPath);
    }



    private JSONObject petToJson(Pet pet) {
        JSONObject object = new JSONObject();
        object.put("id", pet.getId());
        object.put("category", pet.getCategory());
        object.put("name", pet.getName());
        object.put("photoUrls", pet.getPhotoUrls());
        object.put("tags", pet.getTags());
        object.put("status", pet.getStatus());
        return object;
    }


}

