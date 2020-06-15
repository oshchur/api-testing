package builders;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserBuilderImpl implements UserBuilder {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public User getResult() {
        return new User(id, userName, firstName, lastName, email, password, phone, userStatus);
    }

    public List<User> getListResult(final int quantity) {
        final UserDirector userDirector = new UserDirector();

        final List<User> users = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            userDirector.constructRandomUser(this);
            User result = getResult();

            users.add(result);
        }

        return users;
    }
}
