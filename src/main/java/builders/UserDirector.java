package builders;

import com.github.javafaker.Faker;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDirector {
    Faker faker = new Faker();

    public void constructRandomUser(UserBuilder builder) {
        final String userName = faker.name().username();
        builder.setId(faker.number().numberBetween(0, 666));
        builder.setUserName(userName);
        builder.setFirstName(faker.name().firstName());
        builder.setLastName(faker.name().lastName());
        builder.setEmail(userName + faker.number().numberBetween(0, 666) + "@gmail.com");
        builder.setPassword(faker.pokemon().name());
        builder.setPhone(faker.phoneNumber().cellPhone());
        builder.setUserStatus(faker.number().numberBetween(0, 10));
    }

    public List<User> createRandomUsers(final int quantity) {
        final UserBuilderImpl userBuilderImpl = new UserBuilderImpl();
        final List<User> users = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            constructRandomUser(userBuilderImpl);
            User result = userBuilderImpl.getResult();

            users.add(result);
        }

        return users;
    }
}
