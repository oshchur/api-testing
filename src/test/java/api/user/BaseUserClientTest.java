package api.user;

import builders.UserBuilder;
import client.UserClient;
import org.testng.annotations.BeforeClass;

public abstract class BaseUserClientTest {
    UserClient userClient;
    UserBuilder userBuilder;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userBuilder = new UserBuilder();
    }
}
