package model;




import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public User(int id, String userName, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }
    public User(){};

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(final int userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", User Name='" + userName + '\'' +
                ", First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Email='" + email + '\'' +
                ", Password='" + password + '\'' +
                ", Phone='" + phone + '\'' +
                ", User Status=" + userStatus +
                '}';
    }

    protected JSONObject jesika = new JSONObject();

    public JSONObject getJson() {
        jesika.put("id", id);
        jesika.put("userName", userName);
        jesika.put("firstName", firstName);
        jesika.put("lastName", lastName);
        jesika.put("email", email);
        jesika.put("password", password);
        jesika.put("phone",phone);
        jesika.put("userStatus", userStatus);
        return jesika;
    }


}
