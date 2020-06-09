package client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetClient extends BaseClient {

    Response getPetByStatus(String status) {
        return given()
                .queryParam("status", status)
                .get("findByStatus");
    }
}
