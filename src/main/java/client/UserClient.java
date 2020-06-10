package client;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class UserClient extends BaseClient {
    private final String createWithListUrl = "/user/createWithList";
    private final String getUserByUsernameUrl = "/user/{username}";

    public Response createUsersWithList(final List<User> users) {
        final JSONArray requestParams = new JSONArray();
        for (final User user : users) {
            requestParams.put(userToJson(user));
        }

        return given(baseRequestSpecification)
                .body(requestParams.toString())
                .post(createWithListUrl);
    }

    public Response getUserByUserName(final String username) {
        return given(baseRequestSpecification)
                .get(getUserByUsernameUrl, username);
    }

    public Response updateUserByUsername(final String username, final User user) {
        return given(baseRequestSpecification)
                .body(userToJson(user).toString())
                .put(getUserByUsernameUrl, username);
    }

    private JSONObject userToJson(final User user) {
        JSONObject object = new JSONObject();
        object.put("id", user.getId());
        object.put("username", user.getUserName());
        object.put("firstname", user.getFirstName());
        object.put("lastname", user.getLastName());
        object.put("email", user.getEmail());
        object.put("password", user.getPassword());
        object.put("phone", user.getPhone());
        object.put("userstatus", user.getUserStatus());

        return object;
    }

    public List<User> createRandomUsers() {
        final Faker faker = new Faker();
        final List<User> users = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            final User user = new User();
            user.setId(i++);
            user.setUserName(faker.gameOfThrones().character());
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail("tarantino.number" + i + "@gmail.com");
            user.setPassword(faker.pokemon().name());
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setUserStatus(faker.random().nextInt(1));

            users.add(user);
        }

        return users;
    }
}
