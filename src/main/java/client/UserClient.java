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

public class UserClient extends BaseClient {
    private final String createWithListUrl = "/user/createWithList";

    public Response createUsersWithList(final List<User> users) {
        final JSONArray requestParams = new JSONArray();
        for (final User user : users) {
            JSONObject object = new JSONObject();
            object.put("id", user.getId());
            object.put("username", user.getUserName());
            object.put("firstname", user.getFirstName());
            object.put("lastname", user.getLastName());
            object.put("email", user.getEmail());
            object.put("password", user.getPassword());
            object.put("phone", user.getPhone());
            object.put("userstatus", user.getUserStatus());

            requestParams.put(object);
        }

        return given(baseRequestSpecification)
                .body(requestParams.toString())
                .post(createWithListUrl);
    }

    public List<User> createRandomUsers() {
        final Faker faker = new Faker();
        final List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            final User user = new User();
            user.setId(i);
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
