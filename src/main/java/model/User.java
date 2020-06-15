package model;


public class User {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public User() {

    }

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

    public int getId() {
        return id;
    }


    public String getUserName() {
        return userName;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getPhone() {
        return phone;
    }

    public int getUserStatus() {
        return userStatus;
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
}
