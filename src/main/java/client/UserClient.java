package client;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseClient {
    private final String userUrl = "/user";
    private final String createWithUserListUrl = userUrl + "/createWithList";
    private final String getByUsernameUrl = userUrl + "/{username}";
    private final String loginUrl = userUrl + "/login";
    private final String logoutUrl = userUrl + "/logout";

    public Response createWithList(final List<User> users) {
        return given(baseRequestSpecification(ContentType.JSON))
                .body(users)
                .post(createWithUserListUrl);
    }

    public Response getUserByUserName(final String username) {
        return given(baseRequestSpecification(ContentType.JSON))
                .pathParam("username", username)
                .get(getByUsernameUrl);
    }

    public Response updateByUsername(final String username, final User user) {
        return given(baseRequestSpecification(ContentType.JSON))
                .body(user)
                .pathParam("username", username)
                .put(getByUsernameUrl);
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

    public Response login(String name, String password) {
        return given(baseRequestSpecification(ContentType.JSON))
                .queryParam("name", name, "password", password)
                .get(loginUrl);
    }

    public Response logout() {
        return given(baseRequestSpecification(ContentType.JSON))
                .get(logoutUrl);
    }

    public Response create(User user) {
        return given(baseRequestSpecification(ContentType.JSON))
                .body(user)
                .post(userUrl);
    }

}
