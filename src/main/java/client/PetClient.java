package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Pet;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class PetClient extends BaseClient {
    private final String petClientUrl = "/pet";
    private final String petIdUrl = petClientUrl + "​/{petId}";
    private final String petStatusUrl = petClientUrl + "/findByStatus";
    private final String deletePetUrl = petClientUrl + "/{petID}";
    private final String petImageUrl = petIdUrl + "/uploadImage";

    public Response getPetByStatus(String status) {
        return given(baseRequestSpecification(ContentType.JSON))
                .formParam("sold", status)
                .formParam("pending", status)
                .formParam("available", status)
                .get(petStatusUrl);
    }

    public Response getPetById(String id) {
        return given(baseRequestSpecification(ContentType.JSON))
                .pathParam("petId", id)
                .get(petIdUrl);
    }

    //TODO: need fix
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

    public Response updatePet(String id, String name, String status) {
        return given(requestSpecification.contentType("multipart/form-data"))
                .formParam("PetId", id)
                .formParam("Name", name)
                .formParam("Status", status)
                .post(petIdUrl);

    }

    public Response updatePetByImage(Pet pets, String id) {
        return given(requestSpecification.contentType("multipart/form-data")).
                 multiPart("file to upload", new File("/home/Nick/image.jpg"))
                .formParam("id", id)
                .body(pets)
                .post(petImageUrl);
    }
}

