package assertion;

import model.User;
import org.testng.asserts.SoftAssert;

public class UserAssertion {

    private UserAssertion() {

    }

    public static void checkValidUser(final User user) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(user.getUsername(), "Malina", "Error - incorrect username");
        softAssert.assertEquals(user.getFirstName(), "Taras", "Error - incorrect first name");
        softAssert.assertEquals(user.getLastName(), "Malynovskyi", "Error - incorrect last name");
        softAssert.assertEquals(user.getEmail(), "tarasmalynovskyy@gmail.com", "Error - incorrect email");
        softAssert.assertEquals(user.getPhone(), "+3806333333", "Error - incorrect phone");
        softAssert.assertAll();
    }

    public static void checkInvalidUser(final User user) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(user.getUsername(), null, "Error - incorrect username");
        softAssert.assertEquals(user.getFirstName(), null, "Error - incorrect first name");
        softAssert.assertEquals(user.getLastName(), null, "Error - incorrect last name");
        softAssert.assertEquals(user.getEmail(), null, "Error - incorrect email");
        softAssert.assertEquals(user.getPhone(), null, "Error - incorrect phone");
        softAssert.assertAll();
    }
}
