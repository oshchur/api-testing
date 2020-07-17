package builders;

import com.github.javafaker.Faker;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserBuilder {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }


    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }


    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }


    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }


    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }


    public UserBuilder setUserStatus(int userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(userStatus);

        return user;
    }

    public User constructRandomValidUser() {
        final Faker faker = new Faker();
        final String fakeUsername = faker.name().username();

        return this.setId(faker.number().numberBetween(0, 666))
                .setUserName(fakeUsername)
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(fakeUsername + faker.number().numberBetween(0, 666) + "@gmail.com")
                .setPassword(faker.pokemon().name())
                .setPhone(faker.phoneNumber().cellPhone())
                .setUserStatus(faker.number().numberBetween(0, 10))
                .build();
    }

    public User constructRandomInvalidUser() {
        final Faker faker = new Faker();
        final String fakeUsername = faker.name().username();

        return this.setId(faker.number().numberBetween(-150, 0))
                .setUserName(" !! " + faker.number().randomNumber())
                .setFirstName("" + faker.number().numberBetween(-150, 150))
                .setLastName("ss_" + faker.number().numberBetween(-150, 150))
                .setEmail("$$112__" + fakeUsername + faker.number().numberBetween(0, 666))
                .setPassword("")
                .setPhone(faker.name().username())
                .setUserStatus(faker.number().numberBetween(-450, 10))
                .build();
    }

    public List<User> constructRandomListValidUsers(final int quantity) {
        final List<User> users = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            users.add(constructRandomValidUser());
        }

        return users;
    }

    public List<User> constructRandomListInvalidUsers(final int quantity) {
        final List<User> users = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            users.add(constructRandomInvalidUser());
        }

        return users;
    }
}
