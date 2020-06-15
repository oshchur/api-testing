package builders;

public interface UserBuilder {
    void setId(final int id);

    void setUserName(final String userName);

    void setFirstName(final String firstName);

    void setLastName(final String lastName);

    void setEmail(final String email);

    void setPassword(final String password);

    void setPhone(final String phone);

    void setUserStatus(final int userStatus);
}
