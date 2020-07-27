package client;

import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import model.User;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseClient {
    private static final String USER_URL = "/user";
    private static final String CREATE_WITH_USER_LIST_URL = USER_URL + "/createWithList";
    private static final String GET_BY_USERNAME_URL = USER_URL + "/{username}";
    private static final String DELETE_URL = GET_BY_USERNAME_URL;
    private static final String LOGIN_URL = USER_URL + "/login";
    private static final String LOGOUT_URL = USER_URL + "/logout";

    public Response createWithList(final List<User> users) {
        return given(baseRequestSpecification())
                .body(users)
                .post(CREATE_WITH_USER_LIST_URL);
    }

    public Response getUserByUsername(final String username) {
        return given(baseRequestSpecification())
                .pathParam("username", username)
                .get(GET_BY_USERNAME_URL);
    }

    public Response updateByUsername(final String username, final User user) {
        return given(baseRequestSpecification(LogDetail.BODY))
                .body(user)
                .pathParam("username", username)
                .put(GET_BY_USERNAME_URL);
    }

    public Response login(String name, String password) {
        return given(baseRequestSpecification())
                .queryParam("name", name, "password", password)
                .get(LOGIN_URL);
    }

    public Response logout() {
        return given(baseRequestSpecification())
                .get(LOGOUT_URL);
    }

    public Response create(User user) {
        return given(baseRequestSpecification())
                .body(user)
                .post(USER_URL);
    }

    public Response delete(String userName) {
        return given(baseRequestSpecification())
                .pathParam("username", userName)
                .delete(DELETE_URL);
    }

}
